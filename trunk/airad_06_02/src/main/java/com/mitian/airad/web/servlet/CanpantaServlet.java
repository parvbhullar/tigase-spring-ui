package com.mitian.airad.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;

import com.mitian.airad.common.utils.CaptchaUtil;

/**
 * CanpantaServlet.java
 * 
 * @author baojun
 */
public class CanpantaServlet extends HttpServlet {

    /**
	 * 
	 */
    private static final long serialVersionUID = 2166060615659605895L;
    private static int _width = 105;
    private static int _height = 40;

    private static final List<Color> COLORS = new ArrayList<Color>(2);
    private static final List<Font> FONTS = new ArrayList<Font>(3);
    // 定义验证码的字符表
    private static final char[] VALIDATE_CHAR_LIB =
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'K', 'L', 'M', 'N', 'P', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                    'Z', '2', '3', '4', '5', '6', '8', '9'};
    // 验证码过期时间为180秒
    static {
        COLORS.add(Color.GRAY);
        FONTS.add(new Font("Courier New", Font.BOLD, 36));
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        if (getInitParameter("captcha-height") != null) {
            _height = Integer.valueOf(getInitParameter("captcha-height"));
        }

        if (getInitParameter("captcha-width") != null) {
            _width = Integer.valueOf(getInitParameter("captcha-width"));
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Captcha captcha = buildAndSetCaptcha(session);
        CaptchaServletUtil.writeImage(resp, captcha.getImage());
    }

    private Captcha buildAndSetCaptcha(HttpSession session) {
        DefaultWordRenderer wordRenderer = new DefaultWordRenderer(COLORS, FONTS);
        DefaultTextProducer textProducer = new DefaultTextProducer(4, VALIDATE_CHAR_LIB);
        AirAdFishEyeGimpyRenderer renderer = new AirAdFishEyeGimpyRenderer();
        Captcha captcha =
                new Captcha.Builder(_width, _height).addText(textProducer, wordRenderer).gimp(renderer).addBorder()
                        .addNoise().addBackground().build();
        session.setAttribute(CaptchaUtil.CAPTCHA_KEY_IN_SESSION, captcha);
        return captcha;
    }

}
