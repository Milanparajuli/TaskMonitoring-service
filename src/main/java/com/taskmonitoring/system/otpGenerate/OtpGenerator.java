package com.taskmonitoring.system.otpGenerate;

public class OtpGenerator {
	public String otpGenerate() {
		int randomPin =(int) (Math.random()*9000)+1000;
		String otp = String.valueOf(randomPin);
		System.out.println(otp);
		return otp;
	}
}
