package com.eeit162.FWBweb.zh.messenger.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eeit162.FWBweb.zh.messenger.model.bean.Message;
import com.eeit162.FWBweb.zh.messenger.model.bean.OutputMessage;
import com.eeit162.FWBweb.zh.messenger.model.service.MsgService;

@RestController
@RequestMapping(value = "/system", method = RequestMethod.POST)
public class ChatSystemController {

	@Autowired
	private MsgService msgService;

	@RequestMapping("/broadcast")
	public OutputMessage broadcast(@RequestBody Message message) {
		OutputMessage outputMessage = new OutputMessage(new Date().toString(), message);
		System.out.println("1");
		msgService.broadcast(outputMessage);

		return outputMessage;
	}

//	@RequestMapping("/send/{user}")
//	public OutputMessage broadcast(@PathVariable("user") String user, @RequestBody Message message) {
//		OutputMessage outputMessage = new OutputMessage(new Date().toString(), message);
//		msgService.sendMsgToUser(user, outputMessage);
//		msgService.sendMsgToUser(message.getFrom(), outputMessage);
//		return outputMessage;
//
//	}

}
