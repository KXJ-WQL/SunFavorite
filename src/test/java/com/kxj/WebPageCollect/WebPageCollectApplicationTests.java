package com.kxj.WebPageCollect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.kxj.WebPageCollect.ATO.CollectAto;
import com.kxj.WebPageCollect.entity.CollectEntity;
import com.kxj.WebPageCollect.entity.UserEntity;
import com.kxj.WebPageCollect.repsitory.collectRepsitory;
import com.kxj.WebPageCollect.repsitory.userRepsitory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class WebPageCollectApplicationTests {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	collectRepsitory collectRepsitory;

	@Autowired
	userRepsitory userRepsitory;

	@Autowired
	HttpServletRequest httpServletRequest;

	@Test
	void contextLoads() {

		Boolean delete = stringRedisTemplate.delete("KXJ" + "0f0ci");
		System.out.println(delete);

	}

	@Test
	void test(){
//		UserEntity userEntity = new UserEntity();
//		userEntity.setOpenid("dsadwqdhasjdq");
//		userEntity.setUsername("KXJ"+ RandomUtil.randomString(3));
//		userEntity.setAvater("/images/logo.png");
//		userEntity.setCreatetime(LocalDateTime.now());
//		UserEntity save = userRepsitory.save(userEntity);
//		httpServletRequest.getSession().setAttribute("usercoll",save);
//
//		UserEntity usercoll = (UserEntity)httpServletRequest.getSession().getAttribute("usercoll");

//		List<Date> datelineByCollecteddateID = collectRepsitory.getDatelineByCollecteddateID(8);
//
//		for (Date data:datelineByCollecteddateID){
//
//			System.out.println(data.toString());
//		}

		List<Date> datelineByCollecteddateID = collectRepsitory.getDatelineByCollecteddateID(8);

		List<CollectAto> dateline = new ArrayList<>();


		for (Date data:datelineByCollecteddateID){

			String parent = DateUtil.format(data,"yyyy年MM月");
			String title = DateUtil.format(data,"yyyy年MM月dd日");
			Optional<CollectAto> first = dateline.stream().filter((x) -> x.getTitle().equals(parent)).findFirst();
			if(first.isPresent()){
				first.get().getCollectAtoes().add(null);
			}
			System.out.println(parent+"\n"+title+"\n"+first.isPresent());
		}
	}

	@Test
	void  test1(){

		String dateline="All";

		Optional<CollectEntity> all = collectRepsitory.findOne(new Specification<CollectEntity>() {
			@Override
			public Predicate toPredicate(Root<CollectEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate conjunction = criteriaBuilder.conjunction();
				Join<CollectEntity, UserEntity> userid = root.join("user", JoinType.LEFT);
				Path<Object> id = userid.get("id");
				conjunction.getExpressions().add(criteriaBuilder.equal(id, 3));
				if(!dateline.equals("All")){
					LocalDate yy = LocalDate.parse("2022-09-14", DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
					conjunction.getExpressions().add(criteriaBuilder.equal(root.get("collecteddate"), yy));
				}
				UserEntity userentity =(UserEntity) httpServletRequest.getSession().getAttribute("userentity");
				conjunction.getExpressions().add(criteriaBuilder.equal(root.get("personal"), 0));

				return conjunction;
			}
		});

		System.out.println(all.get().toString());

	}


	@Test
	public void  d(){

		String s= "/api/file/wql.txt";
		String substring = s.substring(s.lastIndexOf("/")+1);
		System.out.println(substring);

	}

}
