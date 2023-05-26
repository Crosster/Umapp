function showActivity(response){
    for(let i =0;i<response.length;i++){
        console.log(response[i]);
        let NewsUl = document.getElementById('News');
        let cardLi = document.createElement('li');
        let imgLi = document.createElement('img');
        let hLi = document.createElement('h4');
        let pLi = document.createElement('p');
        let aLi = document.createElement('a');
        let pA = document.createElement('p');

        cardLi.classList.add('list');

        imgLi.classList.add('picture');
        imgLi.src = "${root}/activityImages/福華大飯店2.jpg";

        hLi.classList.add('title');
        hLi.innerHTML =response[i].activityName;

        pLi.classList.add('date');
        pLi.innerText = response[i].activityStartUpTime+"~"+response[i].activityEndTime;

        aLi.href = 'https://www.ymsnp.gov.tw/main_ch/com_eeventcalendar/eeventcalendar.aspx?uid=1427&pid=11';
        aLi.target = '_blank';

        pA.classList.add('content');
        pA.innerText = response[i].activityContent;

        aLi.appendChild(pA);

        cardLi.appendChild(imgLi);
        cardLi.appendChild(hLi);
        cardLi.appendChild(pLi);
        cardLi.appendChild(aLi);

        NewsUl.appendChild(cardLi);
    }


    
}