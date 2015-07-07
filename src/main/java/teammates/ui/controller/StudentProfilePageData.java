package teammates.ui.controller;

import teammates.common.datatransfer.AccountAttributes;
import teammates.common.datatransfer.StudentProfileAttributes;
import teammates.common.util.Const;
import teammates.ui.template.student.profile.EditBox;
import teammates.ui.template.student.profile.UploadPhotoModal;

public class StudentProfilePageData extends PageData {

    private EditBox profileEditBox;
    private UploadPhotoModal uploadPhotoModal;
    
    public StudentProfilePageData(AccountAttributes account, String isEditingPhoto) {
        super(account);
        StudentProfileAttributes profile = account.studentProfile;
        String pictureUrl;
        if (profile.pictureKey.isEmpty()) {
            pictureUrl = Const.SystemParams.DEFAULT_PROFILE_PICTURE_PATH;
        } else {
            pictureUrl = Const.ActionURIs.STUDENT_PROFILE_PICTURE
                       + "?" + Const.ParamsNames.BLOB_KEY + "=" + profile.pictureKey
                       + "&" + Const.ParamsNames.USER_ID + "=" + account.googleId;
        }
        this.profileEditBox = new EditBox(account.name, isEditingPhoto, profile,
                                                        account.googleId, pictureUrl);
        this.uploadPhotoModal = new UploadPhotoModal(account.googleId, pictureUrl, profile.pictureKey);

    }
    
    public EditBox getProfileEditBox() {
        return profileEditBox;
    }

    public UploadPhotoModal getUploadPhotoModal() {
        return uploadPhotoModal;
    }

}
