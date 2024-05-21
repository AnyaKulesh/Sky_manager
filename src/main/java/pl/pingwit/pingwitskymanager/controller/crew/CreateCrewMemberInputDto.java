package pl.pingwit.pingwitskymanager.controller.crew;

public class CreateCrewMemberInputDto {
    private String email;  // почему здесь ты использовала имэйл? я бы здесь ожидал Employee Id. Твой подход работает, но он совсем не типичный для приложений такого типа
    // я предполагаю, что клиент не должен знать id сотрудников, а указывает членов экипажа по уникальному имэйлу

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}