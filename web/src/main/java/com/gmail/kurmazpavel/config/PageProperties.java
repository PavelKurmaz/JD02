package com.gmail.kurmazpavel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class PageProperties {

    @Autowired
    private Environment environment;

    private String errorsPagePath;
    private String startPagePath;
    private String userLoginPagePath;
    private String createRolePagePath;
    private String userSignupPagePath;
    private String userProfilePagePath;
    private String adminProfilePath;
    private String userAddressPagePath;
    private String permissionsPagePath;
    private String permissionsCreatePagePath;
    private String permissionEditPagePath;

    @PostConstruct
    public void initialize() {
        this.errorsPagePath = environment.getProperty("errors.page.path");
        this.startPagePath = environment.getProperty("start.page.path");
        this.userLoginPagePath = environment.getProperty("user.login.page.path");
        this.createRolePagePath = environment.getProperty("role.create.page.path");
        this.userSignupPagePath = environment.getProperty("user.signup.page.path");
        this.userProfilePagePath = environment.getProperty("user.profile.page.path");
        this.adminProfilePath = environment.getProperty("admin.profile.page.path");
        this.userAddressPagePath = environment.getProperty("user.address.page.path");
        this.permissionsPagePath = environment.getProperty("permissions.page.path");
        this.permissionsCreatePagePath = environment.getProperty("permissions.create.page.path");
        this.permissionEditPagePath = environment.getProperty("permissions.edit.page.path");

    }

    public String getPermissionEditPagePath() {
        return permissionEditPagePath;
    }

    public String getPermissionsCreatePagePath() {
        return permissionsCreatePagePath;
    }

    public String getPermissionsPagePath() {
        return permissionsPagePath;
    }

    public String getAdminProfilePath() {
        return adminProfilePath;
    }

    public String getUserAddressPagePath() {
        return userAddressPagePath;
    }

    public String getUserProfilePagePath() {
        return userProfilePagePath;
    }

    public String getErrorsPagePath() {
        return errorsPagePath;
    }

    public String getStartPagePath() {
        return startPagePath;
    }

    public String getUserLoginPagePath() {
        return userLoginPagePath;
    }

    public String getCreateRolePagePath() {
        return createRolePagePath;
    }

    public String getUserSignupPagePath() {
        return userSignupPagePath;
    }
}
