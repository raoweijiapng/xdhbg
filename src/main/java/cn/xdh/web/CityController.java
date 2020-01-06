package cn.xdh.web;

import cn.xdh.entity.City;
import cn.xdh.service.StudentService;
import cn.xdh.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class CityController {
    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/province/{upId}")
    @ResponseBody
    public List<City> getCity(@PathVariable int upId){
        List<City> cityList = studentService.getCityByUpId(upId);
        return cityList;
    }

    @GetMapping("/city/{upId}")
    @ResponseBody
    public List<City> getArea(@PathVariable int upId){
        List<City> cityList = studentService.getAreaByUpId(upId);
        return cityList;
    }

    @ResponseBody
    @GetMapping("/stuprovince/{id}")
    public List<Map<String,Object>> cityName(@PathVariable("id") int id){
        List<Map<String, Object>> cityName = studentService.getCityName(id);
        return  cityName;
    }

    @ResponseBody
    @GetMapping("/stucity/{id}")
    public List<Map<String,Object>> areaName(@PathVariable int id){
        List<Map<String, Object>> areaName = studentService.getAreaName(id);
        return areaName;

    }

}
