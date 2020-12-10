package servlets;

import com.google.gson.Gson;
import daos.CategoryDao;
import models.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = CategoryDao.instOf().findAll();
        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(categories));
    }
}
