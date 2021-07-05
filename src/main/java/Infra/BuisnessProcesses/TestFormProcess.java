package Infra.BuisnessProcesses;

import Infra.Pages.TestFormPage.TestFormPage;
import io.qameta.allure.Step;

public class TestFormProcess {
    TestFormPage testFormPage;

    public TestFormProcess() {
        this.testFormPage = new TestFormPage();
    }

    public void performTestFormProcess(String country, String address, String email, String phone) {
        this.testFormPage.setCountry(country);

        this.testFormPage.setAddress(address);

        this.testFormPage.setEmail(email);

        this.testFormPage.setPhone(phone);

        this.testFormPage.pressSaveButton();

    }


    public void clearAllFields() {
        this.testFormPage.clearAddressField();
        this.testFormPage.clearEmailField();
        this.testFormPage.clearPhoneField();
    }

}
