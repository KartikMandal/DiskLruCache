package com.man.cert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.x509.AlgorithmId;
import sun.security.x509.CertificateAlgorithmId;
import sun.security.x509.CertificateSerialNumber;
import sun.security.x509.CertificateValidity;
import sun.security.x509.CertificateVersion;
import sun.security.x509.CertificateX509Key;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

public class Certificate {
	
public static void main(String[] args) {
	//GenerateKey gg=new GenerateKey();
	//GenerateKey.main();
		/*String blob="";
		 StringTokenizer st = new StringTokenizer(blob, SEPERATOR);
		    int count = st.countTokens();

		     for security, do not print the blob in the exception 
		    if (count != 14) {
		      throw new Exception("count mismatch: "
		        + Integer.toString(count));
		    }

		    String keyName = getValueString(st.nextToken(), KEYID_KEY);
		    long cobrandId = getValueLong(st.nextToken(), COBRANDID_KEY);

		   
		    //HACKHACK since before keys were stored in the blob using seconds !!

		    long creationTime = getValueLong(st.nextToken(), CREATE_KEY) * 1000;
		    long startTime = getValueLong(st.nextToken(), START_KEY) * 1000;
		    long expirationTime = getValueLong(st.nextToken(), EXPIRY_KEY) * 1000;
		    String author = getValueString(st.nextToken(), AUTHOR_KEY);
		    String info = getValueString(st.nextToken(), INFO_KEY);
		    String providerName = getValueString(st.nextToken(), PROVIDER_KEY);
		    String keyAlgorithm = getValueString(st.nextToken(), KEYALG_KEY);
		    if (keyAlgorithm != null && keyAlgorithm.equals(ALGORITHM_OLD)) {
		      keyAlgorithm = "DESede";
		    }
		    if (providerName != null && providerName.equals(PROVIDER_OLD)) {
		      providerName = getEncryptionProvider("DESede");
		    }
		    String cipher = getValueString(st.nextToken(), CIPHER_KEY);
		    String role = getValueString(st.nextToken(), ROLE_KEY);
		    String version = getValueString(st.nextToken(), VERSION_KEY);

		    String keyHex = getValueString(st.nextToken(), KEY_KEY);
		    String ivHex = getValueString(st.nextToken(), IV_KEY);

		     omitting key and iv for security 
		    System.out.println(msgDetails+" "+ new String[] { KEYID_KEY + DELIMITER + keyName
		        + SEPERATOR + COBRANDID_KEY + DELIMITER + cobrandId + SEPERATOR
		        + CREATE_KEY + DELIMITER + creationTime + SEPERATOR + START_KEY
		        + DELIMITER + startTime + SEPERATOR + EXPIRY_KEY + DELIMITER
		        + expirationTime + SEPERATOR + AUTHOR_KEY + DELIMITER + author
		        + SEPERATOR + INFO_KEY + DELIMITER + info + SEPERATOR + PROVIDER_KEY
		        + DELIMITER + providerName + SEPERATOR + KEYALG_KEY + DELIMITER
		        + keyAlgorithm + SEPERATOR + CIPHER_KEY + DELIMITER + cipher
		        + SEPERATOR + ROLE_KEY + DELIMITER + role + SEPERATOR + VERSION_KEY
		        + DELIMITER + version + SEPERATOR });*/
		
		String privateKeyContent=null;
		String abc= null;
		try {
			
			InputStream is = new FileInputStream("C:\\Users\\kmandal\\Desktop\\private.key");
			
			BufferedReader buf = new BufferedReader(new InputStreamReader(is)); 
			String line = buf.readLine(); StringBuilder sb = new StringBuilder(); 
			while(line != null){ sb.append(line).append("\n"); line = buf.readLine(); } 
			 privateKeyContent = sb.toString();

		} catch (IOException e) {
			e.printStackTrace();
		} 
       
		KeyPair kp= null;
		//String k="MIIEogIBAAKCAQEAlNyj9hgw5704gAv5TQrjuJmqABvWIFjH03RHqYYmTsQq6Eak2rusqgPdhAJYtywj049k105v9zg/g9cQRbEwZwECDEkUz9sI4+we8cq0w2+5XK9cPU+4EDqgmd54ufaHfaV5zlB5c/0pRMaTeJHVXb4wTYYuPRVfev//FC30Q4Ahk+ecOue7lL+v3jZ/Kbc0S/PXZmv0ROvB880s5fS551nyW9/dVeoJT1kvrk++bM236pCLooXOsCsT7kH1g1/79enPE700YnjV8b4L/rKrpr89m0qdhrqy+ZPXwToen2Jb0Ub/mNDavdlN8BuCHqHfrl8wPwoRNYQUIecH7XwVbwIDAQABAoIBAEnkN9MJcQUnDOuqheYzOJRCWntRVfmaeB5DQusoNs20XCQOJPiaXfvKjWNgks7hryrtq72gJr3NguGbmG0vpfoLKwwUHquKayBP7ZBR8WZth9I1DZE1iAUuRNf78WtFrz20LQE3OwJTMO/9regUY5fztyBUMZGKkWmJtwE6SlxSdEWWGSWVmBErADcBQ0XfJ9Z64sk1JdMCqOY7MNxFijwzhSgVFFCTFilTMGcMUMDmtT2+0VmHxY+8rKcMYCPOKHQxpFVTPXfApnPlJMgbqz12cIkg/eYe7RNUqo/vy/LJfMx3TuNubFQNuBVc+M2tvz1LE87fnj/JLo+I2I5F6BECgYEA1Ww3VoTYVJ2txKKMG292v7HEYVy4Kln62cYcUOsSV/NdOSJG0clnxVYutFu+Mu5X3l9V950xRtQnvQyn05AxW6uOCreT6iis7+t2ttqCd18asJdzGPG8Kq/rNLIUEcr1Qy10EZG+4BD30GOH01uQaEYpJKjjDruupwkus4zhobcCgYEAso83OBuBwWT7SpItniYnYsPjOlsCd4gU5P4CZMM4hGxUCCqOuaWN1eyUeh/gP52Idbd8Z7aYgYlJsl+PS4HECGhvQ2d1Du4FXAGjjIg+kCuFHtKtbpoSSHb5DixFQNWMTT9SocRj5NN42Ya2t0uRGeT8cLmDobnjKdbBYPOlygkCgYBguaji11ZMEyJcCedBz+SbwG9mSOVWnG0qIbVXnxIzNiK78CL0lTkooISOTT4ISykbqU2G6YEZvrE75u5w3drg1wGLLeQlrcWi/q5Q9PtIKpwoCJurZHOJTS1mNocOAVPi3EjMZaeCWW5WzaUTT15FQ/cFnF07oifnoCvASEtCRQKBgDTuFT1RkhYk/ASv7kvaHYS+coYi4klSkncLAOSjpgZDImnFS8emVrQaTC0jc9MHvKLAgGUyEGUdsDXq88w7LEQdyRWe/mktj3MlnTiUTo9oBy8K3pRRoafPdKaJ4wGEC/H+Gl2PnNkBAurgbxtIkDuv0FEN/QIc20TUv0AhGX0ZAoGALUCwSinno84DOWP7K25fsQ1mb/3pD3Cfu5a5N/YwMY2X57OVR8Z0aDBvLIEYH3v/O3BiFHJTpLjFXVWl8/Stwt4cYTlQ54AeLBlfBry9HrSaV1z07YmQn/sthzPDFDyf86S0ekmvmiuDUo/OFz3n9KDRLH6pVGRq21OH+H+eECs=";
		kp= createKeyFromPemString(privateKeyContent);

//		kp= CryptoUtil.recoverRsaKey(bPublicKey, bPrivateKey);
		try {
			X509Certificate cert= generateSelfSignedX509Certificate( kp, 99L, new Date(), new Date(), new Date(),"kartik", "none", "Java");
			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




	private static KeyFactory getKeyFactoryInstance() throws NoSuchAlgorithmException {
		return KeyFactory.getInstance("RSA");
	}
	/**
	 * Utility method to construct a KeyPair from the given Private Key in PEM Format
	 * @param spemkey
	 * @return
	 */
	public static KeyPair createKeyFromPemString(String spemkey){
		KeyPair keyPair= null;
		PublicKey publicKey = null;
    	PrivateKey privateKey=null;
    	Cipher cipher = null;
    	if(spemkey != null){
	    	StringReader strReader= new StringReader(spemkey);
			PemReader pemReader= new PemReader(strReader);
			try {
					Object pemObject = pemReader.readPemObject();
					if(pemObject instanceof PrivateKey){
						privateKey = (PrivateKey)pemObject;
					}
					PemObject pemObj =(PemObject) pemObject;
					byte[] pemContent = pemObj.getContent();
					
					/* Add PKCS#8 formatting */
				    ASN1EncodableVector v = new ASN1EncodableVector();
				    v.add(new ASN1Integer(0));
				    ASN1EncodableVector v2 = new ASN1EncodableVector();
				    v2.add(new ASN1ObjectIdentifier(PKCSObjectIdentifiers.rsaEncryption.getId()));
				    v2.add(DERNull.INSTANCE);
				    v.add(new DERSequence(v2));
				    v.add(new DEROctetString(pemContent));
				    ASN1Sequence seq = new DERSequence(v);
				    byte[] privKey = seq.getEncoded("DER");
				    
				    PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(privKey);
				    KeyFactory keyFactory = getKeyFactoryInstance();
				    if(privateKey==null){
				     privateKey = keyFactory.generatePrivate(encodedKeySpec);
				    }
				    
				    if(publicKey==null){
				 		try {
				 			cipher = Cipher.getInstance("RSA");
				 		} catch (NoSuchAlgorithmException e1) {
				 			throw new InvalidKeyException(
				 			          "Failed to obtain X.509 encoded form of private key " + privateKey
				 			              + " of class " + privateKey.getClass().getName(),
				 			          e1);
				 		} catch (NoSuchPaddingException e1) {
				 			// TODO Auto-generated catch block
				 			throw new InvalidKeyException(
				 			          "Failed to obtain X.509 encoded form of private key " + privateKey
				 			              + " of class " + privateKey.getClass().getName(),
				 			          e1);
				 		}
				 	    try {
				 	        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
				 	    } catch (InvalidKeyException e) {
				 	    	System.out.println("Error while fetching public key and private key" + e.getMessage() );
				 	    }
				 	        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
				 	        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(rsaPrivateKey.getModulus(), rsaPrivateKey.getPrivateExponent());
				 	        try {
				 				publicKey = KeyFactory.getInstance("RSA").generatePublic(publicKeySpec);
				 			} catch (InvalidKeySpecException e) {
				 				throw new InvalidKeyException(
					 			          "Failed to obtain X.509 encoded form of public key " + publicKey
					 			              + " of class ",
					 			          e);
				 			} catch (NoSuchAlgorithmException e) {
				 				throw new InvalidKeyException(
					 			          "Failed to obtain X.509 encoded form of public key " + publicKey
					 			              + " of class ",
					 			          e);
				 			}
				    }
					keyPair=new KeyPair(publicKey,privateKey);
			} catch (IOException e) {
				System.out.println("Error while fetching public key and private key" + e.getMessage() + spemkey);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error while fetching public key and private key" + e.getMessage() + spemkey);
			}finally{
				try {
					strReader.close();
					pemReader.close();
				} catch (IOException e) {
					System.out.println("Error while closing connection stream " + e.getMessage());
				}catch (Exception e) {
					System.out.println("Error while fetching public key and private key" + e.getMessage() + spemkey);
				}
			}
    	}
		return keyPair;
	}
	
	
	
	public static X509Certificate generateSelfSignedX509Certificate(KeyPair keyPair, long cobrandId, Date creationDate, Date startDate,
			Date expiryDate, String author, String comment, String role) throws Exception{
	 
	 try {
	 X500Name dnName = new X500Name("kartik mandal", "pd", "yodlee", "rws", "ca", "us");
	// X500Principal dnName = new X500Principal("CN=jad boutros, OU=pd, O=yodlee, L=rws, ST=ca, C=us");
	 PrivateKey privkey = keyPair.getPrivate();
	 X509CertInfo info = new X509CertInfo();
	 
	 Date since = startDate; // Since Now
	 Date until = expiryDate; // Until 
	 
	 CertificateValidity interval = new CertificateValidity(since, until);
	 info.set(X509CertInfo.VALIDITY, interval);
	 info.set(X509CertInfo.SERIAL_NUMBER, new CertificateSerialNumber(BigInteger.valueOf(System.currentTimeMillis())));
	 info.set(X509CertInfo.SUBJECT, dnName);
	 info.set(X509CertInfo.ISSUER, dnName);
	 info.set(X509CertInfo.KEY, new CertificateX509Key(keyPair.getPublic()));
	 info.set(X509CertInfo.VERSION, new CertificateVersion(CertificateVersion.V3));
	 
	 AlgorithmId algo = new AlgorithmId(AlgorithmId.sha512WithRSAEncryption_oid);
	 info.set(X509CertInfo.ALGORITHM_ID, new CertificateAlgorithmId(algo));

	// Sign the cert to identify the algorithm that is used.
	 X509CertImpl cert = new X509CertImpl(info);
	 cert.sign(privkey,"SHA512withRSA");

	// Update the algorithm and sign again
	 algo = (AlgorithmId)cert.get(X509CertImpl.SIG_ALG);
	 info.set(CertificateAlgorithmId.NAME + "." + CertificateAlgorithmId.ALGORITHM, algo);
	 
	 cert = new X509CertImpl(info);
	 cert.sign(privkey, "SHA512withRSA");

	 return cert;
	 
	 } catch (Exception e) {
		 System.out.println("Error while creating new certificate :: " + e.getMessage());
		 e.printStackTrace();
	 }
	return null;
}
	
	
	private static final String KEYID_KEY = "keyId";

	  private static final String COBRANDID_KEY = "cobrandId";

	  private static final String CREATE_KEY = "create";

	  private static final String START_KEY = "start";

	  private static final String EXPIRY_KEY = "expiry";

	  private static final String AUTHOR_KEY = "author";

	  private static final String INFO_KEY = "info";

	  private static final String PROVIDER_KEY = "provider";

	  private static final String KEYALG_KEY = "keyAlg";

	  private static final String CIPHER_KEY = "cipher";

	  private static final String ROLE_KEY = "role";

	  private static final String VERSION_KEY = "version";

	  private static final String KEY_KEY = "key";

	  private static final String IV_KEY = "iv";

	  private static final String DELIMITER = "=";

	  private static final String SEPERATOR = ";";

	  private static final String NODATA = "empty";

	  private static final String msgEEE = "SECU.KEYCONVERSION.INFO.PROCESSING_EEE";

	  private static final String msgDetails = "SECU.KEYCONVERSION.INFO.KEY_BLOB_DETAILS";

	  public static final String ALGORITHM_OLD = "algorithm";

	  public static final String PROVIDER_OLD = "provider";

	  private static final String RSA_DELIMITER = "|";
	  
	
	
	
	
	
	private static String getValueString(String token, String key)
			throws Exception {
		StringTokenizer st = new StringTokenizer(token, DELIMITER);

		if (st.countTokens() != 2) {
			throw new RuntimeException(key);
		}

		if (!key.equals(st.nextToken())) {
			throw new RuntimeException( key);
		}

		return st.nextToken();
	}
	
	
	 private static long getValueLong(String token, String key)
			    throws Exception {

			    String value = getValueString(token, key);
			    long result;

			    try {
			      result = Long.parseLong(value);
			    } catch (NumberFormatException e) {
			      throw new RuntimeException(e.getMessage());
			    }

			    return result;
			  }
	 
	 public static String getEncryptionProvider(String algorithm) {

		    String provider = null;

		    try {
		      KeyGenerator kg = KeyGenerator.getInstance(algorithm);
		      provider = kg.getProvider().getName();
		    } catch (NoSuchAlgorithmException e) {
		      provider = null;
		    }

		    return provider;
		  }

}
