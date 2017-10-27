package gdut.bai.servlet;

import gdut.bai.comment.Constance;
import gdut.bai.dao.DAOFactory;
import gdut.bai.dao.UserDao;
import gdut.bai.entity.UserEntity;
import gdut.bai.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 交由 post 方法统一处理
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置内容类型
        response.setContentType("text/html;charset=utf-8");

        // 文本输出流
        PrintWriter printWriter = response.getWriter();

        // 获取请求提交的内容
        String username = request.getParameter(Constance.USER_NAME);
        String password = request.getParameter(Constance.PASSWORD);

        // 获取 UseDAO
        UserDao userDao = DAOFactory.getInstance().getUserDAO(HibernateUtil.getSession());

        if (username == null || username.length() == 0) {
            printWriter.print(getErrorAlertMsg("用户名不能为空"));
        } else {
            List<UserEntity> userList = userDao.queryInfo(Constance.USER_NAME, username);

            if (userList != null && !userList.isEmpty()){
                for (UserEntity user : userList) {
                    if (user.getUserName().equals(username)) {
                        if (user.getPassword().equals(password)) {
                            printWriter.print("登录成功！");
                            return;
                        } else {
                            printWriter.print(getErrorAlertMsg("密码错误！"));
                        }
                    }
                }
            }

            HibernateUtil.closeSession();

            printWriter.print(getErrorAlertMsg("用户名错误！"));
        }
    }

    private String getErrorAlertMsg(String msg){
        return "<script language='javascript'>alert('" + msg + "'); window.location.href='JSP/login.jsp';</script>";
    }
}
