package com.kxj.WebPageCollect.service.serviceimpl;

import cn.hutool.core.date.DateUtil;
import com.kxj.WebPageCollect.ATO.CollectAto;
import com.kxj.WebPageCollect.entity.CollectEntity;
import com.kxj.WebPageCollect.entity.UserEntity;
import com.kxj.WebPageCollect.repsitory.collectRepsitory;
import com.kxj.WebPageCollect.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.service.serviceimpl
 * @Date 2022/9/14 20:15
 * @Version 1.0
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    collectRepsitory collectRepsitory;

    @Autowired
    HttpServletRequest request;

    @Override
    public List<CollectAto> getDateLineByUserID(Integer id) {

        List<Date> datelineByCollecteddateID = collectRepsitory.getDatelineByCollecteddateID(id);

        List<CollectAto> dateline = new ArrayList<>();

        for (Date data:datelineByCollecteddateID){

            String parent = DateUtil.format(data,"yyyy年MM月");
            String title = DateUtil.format(data,"yyyy年MM月dd日");

            dateline = handleDateline(dateline,parent,title);
        }

        return dateline;
    }

    @Override
    public Page<CollectEntity> findUserCollexts(Integer userID, String dateline, Pageable pageable) {

        Page<CollectEntity> collectRepsitoryAll = collectRepsitory.findAll(new Specification<CollectEntity>() {
            @Override
            public Predicate toPredicate(Root<CollectEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                Predicate conjunction = criteriaBuilder.conjunction();

                Join<Object, Object> join = root.join("user", JoinType.LEFT);

                conjunction.getExpressions().add(criteriaBuilder.equal(join.get("id"), userID));

                if (!dateline.equals("All")) {
                    LocalDate date = LocalDate.parse(dateline, DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
                    conjunction.getExpressions().add(criteriaBuilder.equal(root.get("collecteddate"), date));
                }

                //获取session中的用户
                UserEntity userentity = (UserEntity) request.getSession().getAttribute("userentity");
                //判断用户id和
                boolean per = userentity != null && userentity.getId() == userID;

                if (!per) {
                    conjunction.getExpressions().add(criteriaBuilder.equal(root.get("personal"), 0));
                }

                return conjunction;
            }
        }, pageable);
        return collectRepsitoryAll;
    }

    @Override
    public void deleteById(Integer id) {

        collectRepsitory.deleteById(id);
    }

    @Override
    public CollectEntity findByID(Integer id) {
        Optional<CollectEntity> optional = collectRepsitory.findById(id);

        return optional.isPresent() ?optional.get():null;
    }

    @Override
    public void save(CollectEntity collect) {
        if (collect.getId()==null) {
            collect.setCollectedtime(LocalTime.now());
            collect.setCollecteddate(LocalDate.now());

            collectRepsitory.save(collect);
        } else {

            Optional<CollectEntity> temp = collectRepsitory.findById(collect.getId());

            temp.get().setTitle(collect.getTitle());
            temp.get().setUrl(collect.getUrl());
            temp.get().setNote(collect.getNote());
            temp.get().setUser(collect.getUser());
            temp.get().setPersonal(collect.getPersonal());

            temp.get().setCollecteddate(LocalDate.now());
            collectRepsitory.save(temp.get());
        }
    }

    @Override
    public Page<CollectEntity> collectsqare(Pageable pageable) {

        Page<CollectEntity> allByPersonal = collectRepsitory.findAllByPersonal(0, pageable);
        List<CollectEntity> content = allByPersonal.getContent();
        return allByPersonal;
    }

    private List<CollectAto> handleDateline(List<CollectAto> datelineDtos, String parent, String title) {

        // 需要被添加到上级中的子级
        CollectAto datelineDto = new CollectAto();
        datelineDto.setTitle(title);

        Optional<CollectAto> optional = datelineDtos.stream().filter(vo -> vo.getTitle().equals(parent)).findFirst();

        if (optional.isPresent()) {
            // 如果上级存在，则直接添加到该上级中
            optional.get().getCollectAtoes().add(datelineDto);
        } else {
            CollectAto parentDto = new CollectAto();
            parentDto.setTitle(parent);
            parentDto.getCollectAtoes().add(datelineDto);

            datelineDtos.add(parentDto);
        }
        return datelineDtos;
    }
}
