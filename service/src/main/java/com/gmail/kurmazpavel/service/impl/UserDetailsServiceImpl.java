package com.gmail.kurmazpavel.service.impl;

import com.gmail.kurmazpavel.Role;
import com.gmail.kurmazpavel.User;
import com.gmail.kurmazpavel.dao.RolesDao;
import com.gmail.kurmazpavel.UserPrincipal;
import com.gmail.kurmazpavel.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
    private final UserDao userDao;
    private final RolesDao rolesDao;
    @Autowired
    public UserDetailsServiceImpl(UserDao userDao, RolesDao rolesDao) {
        this.userDao = userDao;
        this.rolesDao = rolesDao;
    }
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) {
        User user = (User)userDao.getCurrentSession().createQuery("from User as u where u.login = :login")
                .setParameter("login", login).getSingleResult();
        Role role = rolesDao.read(user.getRoleId());
        UserPrincipal userPrincipal;
        if (user!= null)
            userPrincipal = new UserPrincipal(user, role);
        else {
            throw new UsernameNotFoundException("User not found");
        }
        return userPrincipal;
    }
}
