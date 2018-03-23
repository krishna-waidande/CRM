package com.krishagni.crm.services.impl;

import java.text.SimpleDateFormat;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import com.krishagni.crm.exception.CRMException;
import com.krishagni.crm.services.EmailService;
import com.krishagni.crm.services.TemplateService;

public class EmailServiceImpl implements EmailService {
	private JavaMailSender mailSender;
	
	private TemplateService templateSvc;
	
	private ThreadPoolTaskExecutor taskExecutor;
	
	private SimpleDateFormat dateFmt = new SimpleDateFormat(DATE_FMT);
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setTemplateSvc(TemplateService templateSvc) {
		this.templateSvc = templateSvc;
	}
	
	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public void sendMail(String tmplkey, String subject, String[] to, Map<String,Object> props) {
		props.put("dateFmt", dateFmt);
		String body = templateSvc.render(getTemplate(tmplkey), props);
		sendMail(body, subject, to);
	}
	
	private void sendMail(String body, String subject, String[] to) {
        	try {
        		final MimeMessage mimeMessage = mailSender.createMimeMessage();
    			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    			message.setSubject(subject);
    			message.setTo(to);
    			message.setText(body, true);
    			taskExecutor.submit(new SendMailTask(mimeMessage));
    		} catch (Exception ex) {
            		throw new CRMException("Error while sending mail :"+ ex.getMessage());
    		}
	}
	
	private String getTemplate(String tmplKey) {
		return TEMPLATE_SOURCE + "/" + tmplKey;
	}
	
	private class SendMailTask implements Runnable {
		private MimeMessage mimeMessage;
		
		public SendMailTask(MimeMessage mimeMessage) {
			this.mimeMessage = mimeMessage;
		}
		
		public void run() {
			try {
				mailSender.send(mimeMessage);
			} catch(Exception ex) {
				throw new CRMException("Error while sending mail :"+ ex.getMessage());
			}
		}
	}
	
	private static final String TEMPLATE_SOURCE = "email-templates";
	
	private static final String DATE_FMT = "dd MMMM yyyy";
}
