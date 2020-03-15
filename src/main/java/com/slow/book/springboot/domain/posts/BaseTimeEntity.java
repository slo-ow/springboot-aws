package com.slow.book.springboot.domain.posts;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 1. JPA Entity 클래스들이 BaseTimeEntity 을 상속할 경우 필드들(CreatedDate,modifiedDate)도 칼럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) // 2. BaseTimeEntity 클래스에 Auditing 기능을 포함 시킨다.
public class BaseTimeEntity { // 모든 Entity 의 상위 클래스가 되어 Entity 들의 createdDate, modifiedDate 를 자동으로 관리하는 역할

    @CreatedDate // 3. Entity 가 생성되어 저장될 때 시간이 자동 저장된다.
    private LocalDateTime createDate;

    @LastModifiedDate // 4. 조회한 Entity 의 값을 변경할 때 시간이 자동 저장된다.
    private LocalDateTime modifiedDate;

}
