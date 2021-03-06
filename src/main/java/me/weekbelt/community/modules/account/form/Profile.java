package me.weekbelt.community.modules.account.form;

import lombok.*;
import me.weekbelt.community.modules.account.Account;
import org.hibernate.validator.constraints.Length;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Profile {

    @Length(max = 35)
    private String bio;

    @Length(max = 50)
    private String occupation;

    @Length(max = 50)
    private String location;

    private String profileImage;

    public Profile(Account account) {
        this.bio = account.getBio();
        this.occupation = account.getOccupation();
        this.location = account.getLocation();
        this.profileImage = account.getProfileImage();
    }
}
