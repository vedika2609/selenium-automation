package testdataobjects;

import lombok.Getter;

import java.util.LinkedHashMap;

/*
 * Created by : vedikagupta
 * Date : 04/10/20
 */
@Getter
public class SignUpTestDataObject {
    private String firstName;
    private String lastName;
    private String password;
    private String day;
    private String month;
    private String year;

    public SignUpTestDataObject(LinkedHashMap data) {
        this.firstName = data.get("FirstName").toString();
        this.lastName = data.get("LastName").toString();
        this.password = data.get("Password").toString();
        String[] dob = data.get("DOB").toString().split("[^\\w\\s]");
        //DD-MM-YYYY format
        this.day = dob[0];
        this.month = dob[1];
        this.year = dob[2];
    }
}