package bootcamp.com.batch170;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import bootcamp.com.batch170.utility.Constanta;
import bootcamp.com.batch170.utility.SessionManager;

public class RegisterActivity extends Activity {
    private Context context = this;

    private EditText inputFullname, inputAge, inputAddress,
            inputPostalCode, inputCity, inputReligion;

    private Spinner spinnerCountry, spinnerHobby;
    private CheckBox checkBoxAgreement;
    private Button buttonRegister;

    private String[] arrayCountry = {
            "- Pilih Negara - ","Afghanistan","Albania","Algeria",
            "Andorra","Angola","Anguilla",
            "Antigua &amp; Barbuda","Argentina",
            "Armenia","Aruba","Australia","Austria",
            "Azerbaijan","Bahamas","Bahrain","Bangladesh",
            "Barbados","Belarus","Belgium","Belize","Benin",
            "Bermuda","Bhutan","Bolivia","Bosnia &amp; Herzegovina",
            "Botswana","Brazil","British Virgin Islands","Brunei",
            "Bulgaria","Burkina Faso","Burundi","Cambodia","Cameroon",
            "Cape Verde","Cayman Islands","Chad","Chile","China",
            "Colombia","Congo","Cook Islands","Costa Rica","Cote D Ivoire",
            "Croatia","Cruise Ship","Cuba","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Estonia","Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia","French West Indies","Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada","Guam","Guatemala","Guernsey","Guinea","Guinea Bissau","Guyana","Haiti","Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan","Jersey","Jordan","Kazakhstan","Kenya","Kuwait","Kyrgyz Republic","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Mauritania","Mauritius","Mexico","Moldova","Monaco","Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Namibia","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","Norway","Oman","Pakistan","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda","Saint Pierre &amp; Miquelon","Samoa","San Marino","Satellite","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","South Africa","South Korea","Spain","Sri Lanka","St Kitts &amp; Nevis","St Lucia","St Vincent","St. Lucia","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor L'Este","Togo","Tonga","Trinidad &amp; Tobago","Tunisia","Turkey","Turkmenistan","Turks &amp; Caicos","Uganda","Ukraine","United Arab Emirates","United Kingdom","Uruguay","Uzbekistan","Venezuela","Vietnam","Virgin Islands (US)","Yemen","Zambia","Zimbabwe"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputFullname = (EditText) findViewById(R.id.inputFullname);
        inputAge = (EditText) findViewById(R.id.inputAge);
        inputAddress = (EditText) findViewById(R.id.inputAddress);
        inputPostalCode = (EditText) findViewById(R.id.inputPostalCode);
        inputCity = (EditText) findViewById(R.id.inputCity);
        inputReligion = (EditText) findViewById(R.id.inputReligion);

        spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
        //set isi spinner cara 2
        ArrayAdapter<String> adapterCountry = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item,
                arrayCountry);
        adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerCountry.setAdapter(adapterCountry);

        spinnerHobby = (Spinner) findViewById(R.id.spinnerHobby);
        //set isi spinner cara 1
        ArrayAdapter<CharSequence> adapterHobby = ArrayAdapter.createFromResource(context,
                R.array.daftar_hobby,
                android.R.layout.simple_spinner_item);
        spinnerHobby.setAdapter(adapterHobby);

        checkBoxAgreement = (CheckBox) findViewById(R.id.checkBoxAgreement);
        checkBoxAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxAgreement.isChecked()){
//                    buttonRegister.setVisibility(View.VISIBLE);
                    buttonRegister.setEnabled(true);
                }
                else{
//                    buttonRegister.setVisibility(View.INVISIBLE);
                    buttonRegister.setEnabled(false);
                }
            }
        });

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiInput();
            }
        });

    }

    private void validasiInput(){
        //logic validasinya
        String fullname = inputFullname.getText().toString().trim();
        String age = inputAge.getText().toString().trim();

        //cek spinner hobby sudah dipilih/belum
        if(spinnerHobby.getSelectedItemPosition() == 0){
            Toast.makeText(context, "Hobby belum dipilih", Toast.LENGTH_SHORT).show();
        }
        //cek spinner country sudah dipilih/belum
        else if(spinnerCountry.getSelectedItemPosition() == 0){
            Toast.makeText(context, "Country belum dipilih", Toast.LENGTH_SHORT).show();
        }
        else if(fullname.length() == 0){
            Toast.makeText(context, "Nama lengkap belum diisi", Toast.LENGTH_SHORT).show();
        }
        else if(age.equalsIgnoreCase("")){
            Toast.makeText(context, "Usia belum diisi", Toast.LENGTH_SHORT).show();
        }
        else{
            //4 mandatory field sudah lengkap
            //next logic
            Bundle bundle = new Bundle();
            bundle.putString(Constanta.KEY_FULLNAME, fullname);
            bundle.putInt(Constanta.KEY_AGE, Integer.parseInt(age));
            bundle.putString(Constanta.KEY_HOBBY, spinnerHobby.getSelectedItem().toString());
            bundle.putString(Constanta.KEY_COUNTRY, arrayCountry[spinnerCountry.getSelectedItemPosition()]);

            //simpan ke sharedpreferences
            SessionManager.saveRegistrationData(context,
                    fullname,
                    Integer.parseInt(age),
                    true
                    );

            Intent intent = new Intent(context, MainMenuActivity.class);
            //fungsi utk clear all activities on stack
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.putExtras(bundle);

            startActivity(intent);
        }
    }
}
