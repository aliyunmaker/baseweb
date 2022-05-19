package baseweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import baseweb.model.Demo;
import baseweb.model.WebResult;
import baseweb.service.DemoService;
import baseweb.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/searchDemo.do")
    public void searchDemo(HttpServletRequest request, HttpServletResponse response) {
        WebResult result = new WebResult();
        try {
            String simpleSearch = request.getParameter("simpleSearch");
            if (StringUtils.isBlank(simpleSearch)) {
                simpleSearch = null;
            }
            List<Demo> list = demoService.searchDemo(simpleSearch);
            result.setTotal(list.size());
            result.setData(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorMsg(e.getMessage());
        }
        outputToJSON(response, result);
    }

    @RequestMapping("/addDemo.do")
    public void addDemo(HttpServletRequest request, HttpServletResponse response) {
        WebResult result = new WebResult();
        try {
            String formString = request.getParameter("formString");
            Demo demo = JsonUtils.parseObject(formString, Demo.class);
            demoService.addDemo(demo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorMsg(e.getMessage());
        }
        outputToJSON(response, result);
    }

    @RequestMapping("/updateDemo.do")
    public void updateDemo(HttpServletRequest request, HttpServletResponse response) {
        WebResult result = new WebResult();
        try {
            String formString = request.getParameter("formString");
            Demo demo = JsonUtils.parseObject(formString, Demo.class);
            demoService.updateDemo(demo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorMsg(e.getMessage());
        }
        outputToJSON(response, result);
    }

    @RequestMapping("/deleteDemo.do")
    public void deleteDemo(HttpServletRequest request, HttpServletResponse response) {
        WebResult result = new WebResult();
        try {
            String id = request.getParameter("id");
            demoService.deleteDemo(Integer.valueOf(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorMsg(e.getMessage());
        }
        outputToJSON(response, result);
    }

    @RequestMapping("/batchDeleteDemo.do")
    public void batchDeleteDemo(HttpServletRequest request, HttpServletResponse response) {
        WebResult result = new WebResult();
        try {
            String idArray = request.getParameter("idArray");
            List<Integer> idList = JsonUtils.parseArray(idArray, Integer.class);
            demoService.batchDeleteDemo(idList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorMsg(e.getMessage());
        }
        outputToJSON(response, result);
    }
}
