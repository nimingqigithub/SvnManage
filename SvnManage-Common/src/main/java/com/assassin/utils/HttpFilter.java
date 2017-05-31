package com.assassin.utils;

import com.assassin.exception.ExceptionUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by assassin on 2015/10/8.
 */
public class HttpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        try {



            HttpServletRequest  httpServletRequest=(HttpServletRequest) request;
            String url = httpServletRequest.getRequestURI();
            if (!StringUtils.isNull(httpServletRequest.getQueryString())) {
                url +="?"+ httpServletRequest.getQueryString();
            }
            BufferedReader bufferedReader=null;
            ServletRequest requestWrapper = null;



            if(url.indexOf("GisqRealEstate-Explorer/Tips")>-1){
                chain.doFilter(request, response);
            }
            else{
                String s;
                String strPostParam="";
                StringBuilder sbPostParam=new StringBuilder();
                if(url.indexOf("excursus/signatureimages/saveSignInfoRequest")==-1 && url.indexOf("mvcService")==-1) {

                    if (request instanceof HttpServletRequest) {
                        requestWrapper = new RequestWrapperUtils((HttpServletRequest) request);
                    }
                    bufferedReader = requestWrapper.getReader();

                    while ((s = bufferedReader.readLine()) != null) {
//                  strPostParam+=s;
                        sbPostParam.append(s);
                        if (sbPostParam.length() > 1024 * 5)
                            break;
                    }
                    bufferedReader.close();
                }
                else{
                    //bufferedReader=request.getReader();
                }
                /**
                 * 解决输出中文是乱码的问题
                 */
                strPostParam=new String(sbPostParam.toString().getBytes(),"UTF-8");
                Map map = new HashMap<>();
                map.put("url", url);
                map.put("method", httpServletRequest.getMethod());
                map.put("post", strPostParam==null?"":StringUtils.trim(strPostParam)+"\r\n");

                /**
                 * 记录URL参数信息，用于调试控制
                 */
                ExceptionUtils.LoadLog(map);
                chain.doFilter(requestWrapper==null?request:requestWrapper, response);
            }



        }
        catch (Exception er){
            ExceptionUtils.LoadLog(er);
            throw new RuntimeException(ExceptionUtils.DealErrorMsg(er));
        }
    }

    @Override
    public void destroy() {
        String str="";

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        String str="";
    }

}
