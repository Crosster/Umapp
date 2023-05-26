package com.eeit162.FWBweb.crs.match.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.eeit162.FWBweb.daka.login.Members;
import com.eeit162.FWBweb.daka.login.MembersDao;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class MemberService {
	@Autowired
	private MembersDao mDAO;

	public Members findMemberById(Integer mID) {
		if (mDAO.findById(mID).isPresent()) {
			return mDAO.findById(mID).get();
		} else {
			return null;
		}
	}
	public Members updateMember(Members Member) {
		return mDAO.save(Member);
	}

	public List<Members> memberList() {
		List<Members> mList = mDAO.findAll();
		return mList;
	}

	public List<Members> normalUserList(String m1sexori, String m1gender) {
		// 性向沒填走預設
		if (m1sexori == null || m1sexori.isEmpty()) {
			if ("M".equals(m1gender)) {
				m1sexori = "F";
			}
			if ("F".equals(m1gender)) {
				m1sexori = "M";
			}
		}
		List<Members> mList = mDAO.findBySexOriAndGender(m1sexori, m1gender);
		return mList;
	}

	// 分頁功能測試
	public Page<Members> memberPage(Integer pageNumber, Integer count) {
		PageRequest pr = PageRequest.of(pageNumber - 1, count);

		Page<Members> page = mDAO.findAll(pr);
		return page;
	}

	// 多條件搜尋
	@PersistenceContext
	private EntityManager entityManager;

	public List<Members> dynamicFind(JsonObject json) throws ParseException {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Members> criteriaQuery = criteriaBuilder.createQuery(Members.class);
		Root<Members> root = criteriaQuery.from(Members.class);

		List<Predicate> dynaQuery = new ArrayList<>();

		Predicate genderPredicate = null;
		// 男
		if (json.get("male") != null && json.get("male").getAsBoolean()) {
			genderPredicate = criteriaBuilder.equal(root.get("gender"), "M");
		}
		// 女
		if (json.get("female") != null && json.get("female").getAsBoolean()) {
			Predicate femalePredicate = criteriaBuilder.equal(root.get("gender"), "F");
			if (genderPredicate != null) {
				// 假如男有打勾，女也有打勾
				genderPredicate = criteriaBuilder.or(genderPredicate, femalePredicate);
			} else {
				// 假如男沒打勾
				genderPredicate = femalePredicate;
			}
		}

		if (genderPredicate != null) {
			dynaQuery.add(genderPredicate);
		}

		Predicate smokingPredicate = null;
		// 抽煙
		if (json.get("smoker") != null && json.get("smoker").getAsBoolean()) {
			smokingPredicate = criteriaBuilder.like(root.get("memberDetail").get("smoking"), "抽");
//		    criteriaBuilder.like(root.get("memberDetail").get("smoking"), "%" + smoking + "%")
		}
		// 不抽煙
		if (json.get("unsmoker") != null && json.get("unsmoker").getAsBoolean()) {
			Predicate unsmokerPredicate = criteriaBuilder.like(root.get("memberDetail").get("smoking"), "不抽");
			if (smokingPredicate != null) {
				smokingPredicate = criteriaBuilder.or(smokingPredicate, unsmokerPredicate);
			} else {
				smokingPredicate = unsmokerPredicate;
			}
		}

		if (smokingPredicate != null) {
			dynaQuery.add(smokingPredicate);
		}

		// 身高(高於)
		if (!json.get("minHeight").getAsString().isEmpty()) {
			Integer heightGT = json.get("minHeight").getAsInt();
			dynaQuery.add(criteriaBuilder.greaterThanOrEqualTo(root.get("height"), heightGT));
		}
		// 身高(低於)
		if (!json.get("maxHeight").getAsString().isEmpty()) {
			Integer heightLT = json.get("maxHeight").getAsInt();
			dynaQuery.add(criteriaBuilder.lessThanOrEqualTo(root.get("height"), heightLT));
		}

		// 體重(高於)
		if (!json.get("minWeight").getAsString().isEmpty()) {
			Integer ageGT = json.get("minWeight").getAsInt();
			dynaQuery.add(criteriaBuilder.greaterThanOrEqualTo(root.get("weight"), ageGT));
		}

		// 體重(低於)
		if (!json.get("maxWeight").getAsString().isEmpty()) {
			Integer ageLT = json.get("maxWeight").getAsInt();
			dynaQuery.add(criteriaBuilder.lessThanOrEqualTo(root.get("weight"), ageLT));
		}

		if (json.get("minAge") != null && !json.get("minAge").getAsString().isEmpty()
				&& !json.get("minAge").getAsString().isBlank()) {

			String ageGT = json.get("minAge").getAsString();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = simpleDateFormat.parse(ageGT);
//			System.out.println("ageGT:" + ageGT);
//			System.out.println(date);
			dynaQuery.add(criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), date));
		}

		if (json.get("maxAge") != null && !json.get("maxAge").getAsString().isEmpty()
				&& !json.get("maxAge").getAsString().isBlank()) {
			String ageLT = json.get("maxAge").getAsString();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = simpleDateFormat.parse(ageLT);
//			System.out.println("ageLT:" + ageLT);
//			System.out.println(date);
			dynaQuery.add(criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), date));
		}

		if (dynaQuery != null && !dynaQuery.isEmpty()) {
			criteriaQuery.where(criteriaBuilder.and(dynaQuery.toArray(new Predicate[0])));
		}
		List<Members> rList = entityManager.createQuery(criteriaQuery).getResultList();
		if (rList != null && !rList.isEmpty()) {
			return rList;
		} else {
			return null;
		}
	}

}
