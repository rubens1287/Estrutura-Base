package br.com.bradesco.framework;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import br.com.bradesco.interfaces.ITestLinkUtils;
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.Platform;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

/**
 * 
 * @author AA
 *	An object of this class must be created and all parameters from ITestLinkUtils should be set.
 */

public class TestLinkUtils implements ITestLinkUtils{
	
	 public  static String notes = "";
     public static  int testPlanID = 2,
    		 			platform = 1,
				 		testCaseID = 0, //filled in the test
				 		buildID = 2, 
				 		result = 2; // 2 = Failed
		

	/**
	 *
	 * this method is the simple overwrite from  <b>br.eti.kinoshita.testlinkjavaapi.TestLinkAPI</b>
	 * 
	 * - Use this method in the finally block, but set ExecutionStatus between Try/Catch block.
	 * 
	 * @param testPlanId - ... Right click in the folder in testLink
	 * @param buildId - ...
	 * @param testCaseId - Id from testCase, Right click in test case in testlink
	 * @param result - 0 = ExecutionStatus.NOT_RUN/ 1 = ExecutionStatus.PASSED/ 2 = ExecutionStatus.FAILED/ 3 = ExecutionStatus.BLOCKED
	 * @param notes - execution message.
	 * @author alx
	 */
	public static void reportTCResult(int testPlanId,int buildId, int testCaseId, int result, String notes ){
		if(isProductionEnvironment){	
			ExecutionStatus resultTest = null;
			if(result == 0){
				resultTest =	ExecutionStatus.NOT_RUN;
			}else if(result == 1){
				resultTest =	ExecutionStatus.PASSED;
			}else if(result == 2){
				resultTest =	ExecutionStatus.FAILED;
			}else if(result == 3){
				resultTest =	ExecutionStatus.BLOCKED;
			}else{
				 new Exception("Invalid Execution Status Number");
			}
			
			Connect().reportTCResult(testCaseId, null,
					testPlanId, resultTest, buildId,
					null, notes,null,null, platform, null,null, false);
		}
	}
	
	
	/**
	 * 
	 * @return TestLinkAPI object connected
	 */
	private static TestLinkAPI Connect(){
	  TestLinkAPI api = null;
      URL testlinkURL = null;
       
       try {
            testlinkURL = new URL(urlTestLink);
       }catch(MalformedURLException mue)   {
            mue.printStackTrace( System.err );
            System.exit(-1);
       }
       
       try {
            api = new TestLinkAPI(testlinkURL, devKey);
       } catch( TestLinkAPIException te) {
            te.printStackTrace( System.err );
            System.exit(-1);
       }
    
     return api;
	}
	
	public ArrayList<String> getPlatformInfoUsingTestPlanId(int tcId){
		ArrayList<String> platforms = new ArrayList<String>();
	       Platform[] p = TestLinkUtils.Connect().getTestPlanPlatforms(tcId);
	       for(Platform p1 : p){
	    	   System.out.println(p1);
	    	   platforms.add(p1.toString());
	       }
	 return platforms;
	}
}