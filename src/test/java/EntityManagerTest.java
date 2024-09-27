import com.itschool.springbootdeveloper.SpringBootDeveloperApplication;
import com.itschool.springbootdeveloper.entity.Member;
import jakarta.persistence.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBootDeveloperApplication.class)
@AutoConfigureDataJpa
public class EntityManagerTest {
    @Test
    public void example() {
        // 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
        // 데이터베이스당 1개, persistence.xml에서 설정 정보를 UnitName을 통해 가져옴
        // @PersistenceUnit
        // @Autowired
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hello");

        // 주의사항 : 엔티티 매니저는 쓰레드 간에 공유 X (사용하고 버려야 함)
        // 요청이 올 때마다 하나 생성하고 쓰고 버림
        // @Autowired
        EntityManager em = emf.createEntityManager(); // 빈 자동 주입

        // 주의사항 : JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();

        // 엔티티는 4가지 상태를 가짐
        // 분리(detached) 상태, 관리(managed) 상태, 비영속(transient) 상태, 삭제된(removed) 상태
        
        try {
            // 비영속(transient) 상태 : 엔티티 매니저가 엔티티를 관리하지 않는 상태
            Member member = new Member("홍길동");

            // 관리(managed) 상태 : 엔티티가 관리되는 상태
            // 영속 상태 (바로 DB에 저장되진 않음)
            em.persist(member);

        } catch (Exception e) {
            tx.rollback();
        }
    }
}
