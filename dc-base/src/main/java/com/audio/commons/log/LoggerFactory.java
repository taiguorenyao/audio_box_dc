/**
 * This file is part of the kernelstudio package.
 * <p>
 * (c) 2014-2015 zhoulin <developer@kernelstudio.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE file
 * that was distributed with this source code.
 *
 * @author zhoulin < developer@kernelstudio.com >
 * @link http://www.kernelstudio.com
 * @version 0.1
 * @since 0.1
 */
package com.audio.commons.log;


import com.audio.util.ConverterUtil;
import com.audio.util.StringUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import java.util.Map;

public class LoggerFactory
{

    private static Logger logger = null;

    private static LoggerFactory loggerFactory = null;

    public static final String MESSAGE_PREFIX_LEFT = "[";

    public static final String MESSAGE_PREFIX_RIGHT = "]";


    /**
     * Logger object
     * @param clazz
     * @return LoggerFactory
     */
    public static LoggerFactory getInstance(Class<?> clazz)
    {
        if (null == loggerFactory)
        {
            loggerFactory = new LoggerFactory();
        }

        logger = Logger.getLogger(clazz.getName());
        return loggerFactory;
    }

    /**
     * Logger error message
     * @param message
     * @return void
     */
    public void log(String message)
    {
        if (isDebug())
        {
            logger.log(Level.DEBUG, message);
        }
        else
        {
            logger.log(Level.INFO, message);
        }
    }

    /**
     * Logger  message
     * @param t
     */
    public void log(Throwable t)
    {
        if (isDebug())
        {
            logger.log(Level.DEBUG, t);
        }
        else
        {
            logger.log(Level.INFO, t);
        }
    }

    /**
     * Logger  message
     * @param message
     * @param t
     */
    public void log(String message, Throwable t)
    {
        if (isDebug())
        {
            logger.log(Level.DEBUG, message, t);
        }
        else
        {
            logger.log(Level.INFO, message, t);
        }
    }

    /**
     * Logger debug message
     * @param message
     * @return void
     */
    public void debug(String message)
    {
        logger.log(Level.DEBUG, message);
    }

    public void debug(Throwable t)
    {
        logger.log(Level.DEBUG, t);
    }

    public void debug(String message, Throwable t)
    {
        logger.log(Level.DEBUG, message, t);
    }

    /**
     * Logger error message
     * @param message
     * @return void
     */
    public void error(String message)
    {
        logger.log(Level.ERROR, message);
    }

    /**
     * Logger error message
     * @param message
     * @param t
     */
    public void error(String message, Throwable t)
    {
        logger.log(Level.ERROR, message, t);
    }

    public void error(Throwable t)
    {
        logger.log(Level.ERROR, t);
    }

    /**
     *  Logger info message
     * @param message
     * @return void
     */
    public void info(String message)
    {
        logger.log(Level.INFO, message);
    }

    /**
     *  Logger message
     * @param priority
     * @param className
     * @param method
     * @param t
     * @return void
     */
    public <T> void log(Priority priority, String className, String method,
            Throwable t)
    {
        String message = messageBuilder(className, method, null, null);
        logger.log(priority, message, t);
    }

    /**
     *  Logger message
     * @param priority
     * @param className
     * @param method
     * @param obj
     * @param t
     * @return void
     */
    public <T> void log(Priority priority, String className, String method,
            Object obj, Throwable t)
    {
        String message = messageBuilder(className, method, obj, null);
        logger.log(priority, message, t);
    }

    /**
     *  Logger message
     * @param priority
     * @param className
     * @param method
     * @param obj
     * @param msg
     * @param t
     *
     * @return void
     */
    public <T> void log(Priority priority, String className, String method,
            Object obj, String msg, Throwable t)
    {
        String message = messageBuilder(className, method, obj, msg);
        logger.log(priority, message, t);
    }

    /**
     * Logger message
     * @param priority
     * @param msg
     * @param t
     * @return void
     */
    public <T> void log(Priority priority, String msg, Throwable t)
    {
        String message = messageBuilder(null, null, null, msg);
        logger.log(priority, message, t);
    }

    /**
     * Logger message
     * @param priority
     * @param className
     * @param method
     * @param map
     * @param msg
     * @param t
     * @return void
     */
    public <T> void log(Priority priority, String className, String method,
            Map<T, T> map, String msg, Throwable t)
    {
        String message = messageBuilder(className, method, map, msg);
        logger.log(priority, message, t);
    }

    /**
     * builder message
     * @param className
     * @param method
     * @param obj
     * @param msg
     * @return String
     */
    @SuppressWarnings("unchecked")
    public <T> String messageBuilder(String className, String method,
            Object obj, String msg)
    {
        StringBuffer buffer = new StringBuffer();
        if (null != className && !"".equals(className))
        {
            buffer.append(MESSAGE_PREFIX_LEFT)
                    .append(className)
                    .append(MESSAGE_PREFIX_RIGHT);
        }
        if (null != method)
        {
            buffer.append(" [ method : " + method + " ] ");
        }
        if (StringUtil.isNotEmpty(msg))
        {
            buffer.append(" [ message : " + msg + " ] ");
        }
        if (null != obj)
        {
            buffer.append(" [ parameters : ");
            if (obj instanceof Map)
            {

                buffer.append(ConverterUtil.mapToJson((Map<T, T>) obj)
                        .toString());
            }
            else
            {
                buffer.append(ConverterUtil.object2Json(obj));
            }
            buffer.append(MESSAGE_PREFIX_RIGHT);
        }
        return buffer.toString();
    }

    /**
     * logger is Debug
     * @return boolean
     */
    public boolean isDebug()
    {
        return logger.isDebugEnabled();
    }
}
