import dao.UserDao;
import entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/login")
//public class LoginServlet extends HttpServlet {
//    private UserDao userDao = new UserDao();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//
//        HttpSession session = req.getSession();
//
//        Integer count = (Integer) session.getAttribute("count");
//
//        if (count == null) {
//            session.setAttribute("count", 1);
//            count = 1;
//        } else {
//            session.setAttribute("count", count + 1);
//        }
//
//
//        try (PrintWriter writer = resp.getWriter()) {
//            writer.write("Connected");
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            String name = req.getParameter("name");
//            String password = req.getParameter("password");
//
//            User userFromDb = userDao.findByName(name);
//
//            if (userFromDb == null || !userFromDb.getPassword().equals(password)) {
//                getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
//            } else {
//                req.setAttribute(
//                        "user", userFromDb);
//                getServletContext().getRequestDispatcher("/success.jsp").forward(req, resp);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
import static java.util.Objects.nonNull;

@WebFilter(filterName = "Login", urlPatterns = "/")
public class LoginServlet implements Filter {
    private UserDao userDao = new UserDao();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        Cookie[] cookies = req.getCookies();
        String message = "";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("message")) {
                    message = c.getValue();
                }
            }
        }

        if (nonNull(session) && nonNull(session.getAttribute("user"))) {
            req.setAttribute("hasCookie", !message.equals(""));
            req.setAttribute("message", message);
            req.getRequestDispatcher("/main.jsp").forward(req, resp);
        } else {
            User user = userDao.findByName(login);
            if (user != null) {
                req.setAttribute("hasCookie", !message.equals(""));
                req.setAttribute("message", message);
                req.getSession().setAttribute("user", user);
                req.getRequestDispatcher("/main.jsp").forward(req, resp);
            } else {
                if (login != null) {
                    req.setAttribute("message", "Неверный логин или пароль");
                }
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
    }
}