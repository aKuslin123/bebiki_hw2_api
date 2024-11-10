package com.example.bebiki_hw2_api.ui.pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.bebiki_hw2_api.ui.data.BankAccount;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class RegisterAccountPage {
    private SelenideElement firstNameInput = $(Selectors.byId("customer.firstName"));
    private SelenideElement lastNameInput = $(Selectors.byId("customer.lastName"));
    private SelenideElement address = $(Selectors.byId("customer.address.street"));
    private SelenideElement city = $(Selectors.byId("customer.address.city"));
    private SelenideElement state = $(Selectors.byId("customer.address.state"));
    private SelenideElement zipCode = $(Selectors.byId("customer.address.zipCode"));
    private SelenideElement phone = $(Selectors.byId("customer.phoneNumber"));
    private SelenideElement ssn = $(Selectors.byId("customer.ssn"));
    private SelenideElement username = $(Selectors.byId("customer.username"));
    private SelenideElement password = $(Selectors.byId("customer.password"));
    private SelenideElement repeatedPassword = $(Selectors.byId("repeatedPassword"));

    private SelenideElement registerButton = $(Selectors.byValue("Register"));

    SelenideElement addressError = $(Selectors.byId("customer.address.street.errors"));
    SelenideElement cityError = $(Selectors.byId("customer.address.city.errors"));
    SelenideElement stateError = $(Selectors.byId("customer.address.state.errors"));
    SelenideElement zipCodeError = $(Selectors.byId("customer.address.zipCode.errors"));
    SelenideElement ssnError = $(Selectors.byId("customer.ssn.errors"));
    SelenideElement usernameError = $(Selectors.byId("customer.username.errors"));
    SelenideElement passwordError = $(Selectors.byId("customer.password.errors"));
    SelenideElement confirmPasswordError = $(Selectors.byId("repeatedPassword.errors"));

    SelenideElement successfulMessageTitle = $("[class*='title']");
    SelenideElement successfulMessageText = $("#rightPanel p");

    public void open() {
        Selenide.open("/parabank/register.htm");
    }

    public void register(BankAccount bankAccount) {
        firstNameInput.val(bankAccount.getFirstName());
        lastNameInput.val(bankAccount.getLastName());
        address.val(bankAccount.getAddress());
        city.val(bankAccount.getCity());
        state.val(bankAccount.getState());
        zipCode.val(bankAccount.getZipCode());
        phone.val(bankAccount.getPhone());
        ssn.val(bankAccount.getSsn());
        username.val(bankAccount.getUsername());
        password.val(bankAccount.getPassword());
        repeatedPassword.val(bankAccount.getRepeatedPassword());

        registerButton.click();
    }
}
