package com.jwt.mainpac.authentification_jwt2.refreshToken;

import com.jwt.mainpac.authentification_jwt2.entity.RefreshToken;
import com.jwt.mainpac.authentification_jwt2.entity.User;
import com.jwt.mainpac.authentification_jwt2.repositories.RefreshRepository;
import com.jwt.mainpac.authentification_jwt2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class RefreshCreator {

    @Value(value="${jwt.refresh.expired}")
    private long expiredDate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RefreshRepository refreshRepository;

    public RefreshToken createRef() {
        String refreshToken = UUID.randomUUID().toString();
        Date startDate = new Date();
        Date finishDate = new Date(startDate.getTime() + expiredDate);
        RefreshToken refreshElement = new RefreshToken(startDate, finishDate, refreshToken);
        refreshRepository.save(refreshElement);
        return refreshElement;
    }

    public void updateRef(User user){
        RefreshToken refreshToken = user.getRefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        Date startDate = new Date();
        refreshToken.setStartDate(new Date());
        refreshToken.setExpiredDate(new Date(startDate.getTime() + expiredDate));
        refreshRepository.save(refreshToken);
    }

}
