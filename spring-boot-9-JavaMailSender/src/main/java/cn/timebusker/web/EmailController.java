package cn.timebusker.web;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

	private final static Logger logger = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping("/simple")
	public void sendSimpleMail() throws Exception {
		logger.info("发送简单的文本邮件...");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("576697722@qq.com");
		message.setTo("1241564291@qq.com");
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");
		mailSender.send(message);
	}

	@RequestMapping("/attchments")
	public void sendAttachmentsMail() throws Exception {
		logger.info("发送带附件的邮件...");
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("576697722@qq.com");
		helper.setTo("576697722@qq.com");
		helper.setSubject("主题：有附件");
		helper.setText("有附件的邮件");
		FileSystemResource file = new FileSystemResource(new File("/static/img/logo.png"));
		helper.addAttachment("附件-1.jpg", file);
		helper.addAttachment("附件-2.jpg", file);
		mailSender.send(mimeMessage);
	}

	@RequestMapping("/inline")
	public void sendInlineMail() throws Exception {
		logger.info("发送嵌入静态资源的邮件...");
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("576697722@qq.com");
		helper.setTo("1241564291@qq.com");
		helper.setSubject("主题：嵌入静态资源");
		helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true);
		FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
		helper.addInline("weixin", file);
		mailSender.send(mimeMessage);
	}

	@RequestMapping("/template")
	public void sendTemplateMail() throws Exception {
		logger.info("发送简单邮件模板的邮件...");
	}
}
