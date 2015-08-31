package com.selenium.course.testng;

import com.selenium.course.pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Rina Espinoza on 8/28/2015.
 */
public class EditAccount {
    private AccountForm accountForm;
    private TabPage accountTab;
    private AccountDetail accountDetail;
    private String accountName = "test001";
    private String accountNameEdited = "test001Edited";

    @BeforeClass
    public void setUp() {
        LoginPage loginPage = new LoginPage();
        ContentPage contentPage = loginPage.loginAsPrimaryUser();
        accountTab = contentPage.tabBar.clickAccounts();
        accountForm = new AccountForm(accountTab.clickNewBtn().getDriver());
        accountForm.setAccountNameText(accountName);
        accountDetail = new AccountDetail(accountForm.clickSaveBtn().getDriver());
    }


    @Test
    public void testEditAccount() {

        accountForm = new AccountForm(accountDetail.clickEditBtn().getDriver());
        accountForm.setAccountNameText(accountNameEdited);
        accountDetail = new AccountDetail(accountForm.clickSaveBtn().getDriver());
        Assert.assertEquals(accountDetail.getObjectName(), accountNameEdited);

    }

    @AfterClass
    public void tearDown() {
        accountTab = accountDetail.clickDeleteBtn();
        accountTab.goToNavigationLinks().clickLogoutBtn();
    }


}
