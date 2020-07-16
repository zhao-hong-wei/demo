package com.dj.ssm.web;

import com.alibaba.fastjson.JSONObject;
import com.dj.ssm.pojo.ResultModel;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import com.dj.ssm.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查一条
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResultModel get(@PathVariable Integer id){
        try {
            User user = userService.getById(id);
            return new ResultModel().success(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }
/*
    @GetMapping
    public ResultModel list(Integer pageNo, Integer pageSize){
        try {
            IPage iPage = new Page(pageNo, pageSize);
            IPage <User> page = userService.page(iPage);
            return new ResultModel().success(page);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }
*/

    /**
     * 增加
     * @param user
     * @return
     */
    @PostMapping
    public ResultModel add(@RequestBody User user) {
        try {
            return new ResultModel().success(userService.save(user));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }



/*
    @PostMapping("list")
    public ResultModel list2(@RequestBody User user){
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("user_name", user.getUserName());
            List<User> userList = userService.list(queryWrapper);
            return new ResultModel().success(userList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }
*/

    /**
     * 修改
     * @param user
     * @return
     */
    @PutMapping
    public ResultModel update(User user){
        try {
            userService.updateById(user);
            return new ResultModel().success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResultModel del(@PathVariable Integer id){
        try {
            userService.removeById(id);
            return new ResultModel().success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }


    @GetMapping("test")
    public ResultModel test(){
        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("id","12");
            String result = HttpClientUtil.sendHttpRequest("http://localhost:8080/get", HttpClientUtil.HttpRequestMethod.GET, params);
            JSONObject obj = JSONObject.parseObject(result);
            if (obj.getInteger("code").equals(200)) {
                return new ResultModel().success(obj.get("data"));
            }else {
                return new ResultModel().error(obj.getString("msg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping("uploadToFile")
    public String uploadToUser(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName.indexOf("\\") != -1) {
            fileName = fileName.substring(fileName.lastIndexOf("\\"));
        }
        // 获取文件存放地址
        String filePath = "E:\\images\\";
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();// 不存在路径则进行创建
        }
        FileOutputStream out = null;
        try {
            // 重新自定义文件的名称
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String d = sdf.format(date);// 时间
            filePath = filePath + d + fileName;
            out = new FileOutputStream(filePath);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            return "error";
        }
        return filePath; // 返回文件地址
    }


}
