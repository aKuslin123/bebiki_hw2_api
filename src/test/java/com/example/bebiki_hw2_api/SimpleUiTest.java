package com.example.bebiki_hw2_api;

import com.codeborne.selenide.*;
import com.example.bebiki_hw2_api.ui.data.BankAccount;
import com.example.bebiki_hw2_api.ui.pages.RegisterAccountPage;
import com.example.bebiki_hw2_api.utils.RandomData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SimpleUiTest {
    @BeforeAll
    public static void setupUiTests() {
        Configuration.baseUrl = "https://parabank.parasoft.com";
        Configuration.browser = "chrome";
        Configuration.timeout = 10;
    }

    RegisterAccountPage registerAccountPage = new RegisterAccountPage();

    // Принципы
    // DRY = Dont repeat yourself
    // Веб элементы не ищутся в тесте, они ищутся в Page object классе
    // Автотест сам создает рандомизированные данные для себя

    @Test
    public void userCanNotCreateAccountWithNameAndSurnameOnly() {
        // Подготовка страницы
        registerAccountPage.open();

        // Подготовка данных
        BankAccount bankAccount = BankAccount.builder()
                .firstName(RandomData.randomString())
                .lastName(RandomData.randomString())
                .build();

        // Шаги теста
        registerAccountPage.register(bankAccount);

        // Проверка ожидаемого результата
        registerAccountPage.getAddressError().shouldHave(Condition.exactText("Address is required"));
        registerAccountPage.getCityError().shouldHave(Condition.exactText("City is required."));
        registerAccountPage.getStateError().shouldHave(Condition.exactText("State is required."));
        registerAccountPage.getZipCodeError().shouldHave(Condition.exactText("Zip Code is required."));
        registerAccountPage.getSsnError().shouldHave(Condition.exactText("Social Security Number is required."));
        registerAccountPage.getUsernameError().shouldHave(Condition.exactText("Username is required."));
        registerAccountPage.getPasswordError().shouldHave(Condition.exactText("Password is required."));
        registerAccountPage.getConfirmPasswordError().shouldHave(Condition.exactText("Password confirmation is required."));
    }

    @Test
    public void userCanCreateAccount() {
        registerAccountPage.open();

        String username = RandomData.randomString();
        String password = RandomData.randomString();

        BankAccount bankAccount = BankAccount.builder()
                .firstName(RandomData.randomString())
                .lastName(RandomData.randomString())
                .address(RandomData.randomString())
                .city(RandomData.randomString())
                .state(RandomData.randomString())
                .zipCode(RandomData.randomString())
                .phone(RandomData.randomPhone())
                .ssn(RandomData.randomString())
                .username(username)
                .password(password)
                .repeatedPassword(password)
                .build();

        registerAccountPage.register(bankAccount);
        registerAccountPage.getSuccessfulMessageTitle().shouldHave(Condition.exactText("Welcome " + username));
        registerAccountPage.getSuccessfulMessageText().
                shouldHave(Condition.exactText("Your account was created successfully. You are now logged in."));
    }
}
