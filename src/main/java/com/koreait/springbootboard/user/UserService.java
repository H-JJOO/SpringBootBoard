package com.koreait.springbootboard.user;

import com.koreait.springbootboard.MyUserUtils;
import com.koreait.springbootboard.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired private MyUserUtils userUtils;


    public int join(UserEntity entity) {
        //유효성 검사

        String hashedUpw = BCrypt.hashpw(entity.getUpw(), BCrypt.gensalt());
        entity.setUpw(hashedUpw);
        try {
            return mapper.insUser(entity);//회원가입성공
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

//        UserEntity copyEntity = new UserEntity();//객체 복사
//        BeanUtils.copyProperties(entity, copyEntity);//깊은 복사! entity 를 copyEntity 로
//
//        //비밀번호 암호화
//        String hashPw = BCrypt.hashpw(entity.getUpw(), BCrypt.gensalt());
//        copyEntity.setUpw(hashPw);//복사된 거에 비밀번호 암호화
//        return mapper.insUser(copyEntity);
    }

    // 0 : DB 에러, 1: 로그인 성공, 2: 아이디 없음. 3: 비밀번호 다름
    public int login(UserEntity entity) {
        UserEntity dbUser = null;
        try {
            dbUser = mapper.selUser(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;// DB 에러
        }
        if (dbUser == null) {
            return 2; //아이디 없음
        } else if (!BCrypt.checkpw(entity.getUpw(), dbUser.getUpw())) {
            return 3; //비밀번호 틀림
        }
        //Server 의 메모리를 덜 쓰게끔(GC : Garbage Collection)
        dbUser.setUpw(null);//저장해둘 필요 없는거
        dbUser.setRdt(null);//저장해둘 필요 없는거
        dbUser.setMdt(null);//저장해둘 필요 없는거
        userUtils.setLoginUser(dbUser);
        return 1; //로그인 성공
    }


}
