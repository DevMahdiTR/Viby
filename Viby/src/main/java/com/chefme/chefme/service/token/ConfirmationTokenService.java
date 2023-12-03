package com.chefme.chefme.service.token;



import com.chefme.chefme.model.token.ConfirmationToken;
import com.chefme.chefme.model.user.UserEntity;
import org.jetbrains.annotations.NotNull;

public interface ConfirmationTokenService {


    public ConfirmationToken fetchTokenByToken(final String token);
    public String generateConfirmationToken(@NotNull UserEntity userEntity);
    public void setConfirmedAt(final String token);
    public void saveConfirmationToken(@NotNull ConfirmationToken confirmationToken);

    public String getConfirmationPage();
    public String getAlreadyConfirmedPage();

}
