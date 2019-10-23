package fptt.example.bookshop;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fptt.example.bookshop.R;

import fptt.example.bookshop.model.User;
import fptt.example.bookshop.repositories.FAccountRepository;
import fptt.example.bookshop.repositories.FAccountRepositoryImp;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    FAccountRepository repon ;
    private TextView txtFullname, txtEmail, txtPhone, txtAddress;
    TextView btnEditProfile;
    User userInfo;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        repon = new FAccountRepositoryImp(getActivity());
        txtFullname = view.findViewById(R.id.txtFullname);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtPhone = view.findViewById(R.id.txtPhone);
        txtAddress = view.findViewById(R.id.txtAddress);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditProfileActivity.class);
                startActivity(intent);
            }
        });

        initView();
        return view;
    }

    private void initView() {
        UserInfo userInformation = (UserInfo) getActivity().getApplication();
        if(userInformation.getEmail() != null && !userInformation.getEmail().isEmpty())
            txtEmail.setText(userInformation.getEmail());
        if(userInformation.getFullname() != null && !userInformation.getFullname().isEmpty())
            txtFullname.setText(userInformation.getFullname());
        if(userInformation.getPhone() != null && !userInformation.getPhone().isEmpty())
            txtPhone.setText(userInformation.getPhone());
        if(userInformation.getListAddress() != null && !userInformation.getListAddress().isEmpty())
            txtAddress.setText(userInformation.getListAddress().get(0) + "...");

    }


}
