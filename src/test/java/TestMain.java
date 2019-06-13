import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

import com.zkdx.database.*;

public class TestMain {

    public static void main(String[] args) {

        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeDAO dao = session.getMapper(EmployeeDAO.class);
        Employee employee = null;
        try {
            //employee = session.selectOne( "getEmployeeById", 12);
            employee = dao.getEmployeeById(12);

            System.out.println(employee);
        } finally {
            session.close();
        }
    }
}
