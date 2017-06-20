package com.csxiaoyao.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.csxiaoyao.springmvc.bean.Card;
import com.csxiaoyao.springmvc.bean.Dept;
import com.csxiaoyao.springmvc.bean.Employee;
import com.csxiaoyao.springmvc.response.EmployeeRespSet;
import com.csxiaoyao.springmvc.response.ResponseSet;
import com.csxiaoyao.springmvc.service.IEmployeeService;
import com.csxiaoyao.springmvc.util.Constants;
import com.csxiaoyao.springmvc.util.exception.BusinessException;
import com.csxiaoyao.springmvc.view.EmployeeForView;

/**
 * @Scope : spring默认Scope是单列模式(singleton),线程不安全
 * @Scope("prototype") : 每个请求创建一个新的实例
 * @Scope("session") : 只要用户不退出,实例就一直存在
 * @Scope("request") : 作用域换成request
 * @RequestMapping : 处理请求地址映射的注解，可用于类或方法上
 */
@Scope("prototype")
@Controller
@RequestMapping("/Employee")
public class EmployeeController {
	/**
	 * 定义常量
	 */
	public static final String FLAG = "flag";
	/**
	 * 加载service
	 */
	@Resource
	private IEmployeeService employeeService;
	/**
	 * 返回json数据，能够接收对象并自动封装
	 */
	@ResponseBody
	@RequestMapping(value="/find",method=RequestMethod.POST)
	public EmployeeRespSet findEmployee(
			@RequestParam("id") int id,
			@RequestParam(value = "param", required = false, defaultValue="test" ) String param, 
//			List<Employee> list,
			//接收对象，自动封装
			Employee emp,
			HttpSession session) throws BusinessException{
		
		System.out.println(emp);
		EmployeeRespSet result = new EmployeeRespSet();
		try {
			Employee employee = employeeService.findById(id);
			result.setCode(Constants.RESP_CODE_SUCCESS);
			result.setMessage(Constants.RESP_MSG_SUCCESS);
			result.setEmployee(new EmployeeForView(employee));
//			session.setAttribute(FLAG, employee.getId());
		} catch (BusinessException e) {
			result.setCode(e.getCode());
			result.setMessage(e.getMsg());
			result.setEmployee(null);
		} catch (NullPointerException e) {
			System.out.println(param);
			throw new BusinessException(007,"方法抛出的异常");
		}
		return result;
	}
	/**
	 * 添加
	 * @throws BusinessException 
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public ResponseSet testPage(Employee emp,@RequestParam("deptId") int deptId, @RequestParam("cardId") int cardId, HttpSession session) throws BusinessException{
		Dept dept = new Dept();
		dept.setDeptId(deptId);
		Card card = new Card();
		card.setCardId(cardId);
		emp.setDept(dept);
		emp.setCard(card);
		employeeService.add(emp);
		ResponseSet responseSet = new ResponseSet(Constants.RESP_CODE_SUCCESS , Constants.RESP_MSG_SUCCESS);
		return responseSet;
	}
	/**
	 * RestFul接收GET参数，返回jsp页面
	 * @RequestMapping(value="/info/{pid}/{pname}",method=RequestMethod.GET)
	 * @PathVariable接收大括号里的值,也可以用bean来接收
	 */
	@ResponseBody
	@RequestMapping(value="/test/info/{info} ",method=RequestMethod.GET)
	public String testPage(@PathVariable String info, HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("info", info);
		System.out.println(info);
		return "testPage";
	}
	/**
	 * method=RequestMethod.PUT
	 * 如果前台用的是application/json类型传进来,就一定要在后台接收参数的对象前面加@RequestBody
	 * 用于把传来的JSON 转换成接收的对象, 如果是form提交就不需要了
	 * 仅作为测试
	 */
	@RequestMapping(value="/info",method=RequestMethod.PUT)
    public ModelAndView fun(@RequestBody Employee employee, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("empId", employee.getId());
		ModelAndView mav=new ModelAndView("list",map);
		return mav;
    }
}