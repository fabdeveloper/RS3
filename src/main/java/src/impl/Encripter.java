package src.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.enterprise.context.RequestScoped;

import src.inter.IEncripter;

@RequestScoped
public class Encripter implements IEncripter {

	@Override
	public String encriptStringToSha256(String encriptable)throws NoSuchAlgorithmException {
		String algoritm = "SHA-256";
		//Crea el "digester"
		MessageDigest md = MessageDigest.getInstance(algoritm);
		md.reset();
		//Calcula el valor del digest
		byte[] digest = md.digest(encriptable.getBytes());
		//Convierte el digest a cadena hexadecimal
		StringBuilder sb = new StringBuilder();
		for(int i=0;i < digest.length;i++) {
		String valHex = Integer.toHexString(digest[i] & 0xFF);
		if (valHex.length()==1)
		sb.append("0");
		sb.append(valHex);
		}
		return sb.toString();		
	}
	
	@Override
	public String encriptStrintToSha256Base64(String encriptable) throws NoSuchAlgorithmException{
		final MessageDigest messageDigest = java.security.MessageDigest.getInstance("SHA-256");
		messageDigest.reset();
		final byte bin[] = messageDigest.digest(encriptable.getBytes());
		return Base64.getEncoder().encodeToString(bin);
	}
	


}
