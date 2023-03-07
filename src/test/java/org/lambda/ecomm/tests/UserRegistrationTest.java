package org.lambda.ecomm.tests;

import io.qameta.allure.*;
import org.lambda.ecomm.base.BaseTest;
import org.lambda.ecomm.constants.AppConstants;
import org.lambda.ecomm.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@Epic("EPIC-09: Registration Page Design")
@Story("US-Registration:10 Registration page and mandatory fields check")
public class UserRegistrationTest extends BaseTest {
    @BeforeClass
    public void registrationSetup() {
        registrationPage = homePage.navigateToRegisterPage();
    }

    @Description("Verify the mandatory fields on registration page")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void mandatoryFieldsTest() {
        List<String> mandatoryFields = registrationPage.checkMandatoryFields();
        Assert.assertEquals(mandatoryFields, AppConstants.MANDATORY_FIELDS_SYMBOL);
    }

    public String getRandomEmail(){
        String email = "automation"+System.currentTimeMillis()+"@gmail.com";
        return email;
    }

    @Description("Do user registration")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "getRegistrationData")
    public void registerUserTest(String firstName, String lastName,String phoneNo ,String password,String subscribe) {
        boolean registerUserSuccessfully=registrationPage.doRegisterUser(firstName,lastName,getRandomEmail(),
                phoneNo,password,subscribe);
        Assert.assertTrue(registerUserSuccessfully);
    }

    @DataProvider
    public Object[][] getRegistrationData(){
        Object regData[][]=ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
        return regData;

    }
}
