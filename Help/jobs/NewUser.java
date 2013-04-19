package jobs;

import play.jobs.Job;
import play.libs.Crypto;

public class NewUser extends Job{
	
	public NewUser(Long id){
		doJob(id);
	}
	
	public void doJob(Long id){
		Crypto crypto = new Crypto();
		String token = crypto.encryptAES(String.valueOf(id));
		System.out.println(token);
	}

}
