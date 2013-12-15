/*
 * Copyright 2007-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.paoding.rose.web.portal.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * 私有请求包装器，但不继承于 {@link HttpServletRequestWrapper}
 * 
 * @author 王志亮 [qieqie.wang@gmail.com]
 * 
 * @author Popo[wyp009@gmail.com] 修改 支持servlet3.0
 */
public class PrivateRequestWrapper implements HttpServletRequest {

    private HttpServletRequest request;

    private Object mutex;

    public PrivateRequestWrapper(HttpServletRequest request) {
        this.request = request;
        this.mutex = request;
    }

    protected HttpServletRequest getRequest() {
        return request;
    }
/*
    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return getRequest().getRequestDispatcher(path);
    }

    @Override
    public Object getAttribute(String name) {
        return getRequest().getAttribute(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Enumeration getAttributeNames() {
        synchronized (mutex) {
            return getRequest().getAttributeNames();
        }
    }

    @Override
    public void removeAttribute(String name) {
        synchronized (mutex) {
            getRequest().removeAttribute(name);
        }
    }

    @Override
    public void setAttribute(String name, Object value) {
        synchronized (mutex) {
            getRequest().setAttribute(name, value);
        }
    }

    @Override
    public String getContextPath() {
        return getRequest().getContextPath();
    }

    @Override
    public String getQueryString() {
        return getRequest().getQueryString();
    }

    @Override
    public String getRequestURI() {
        return getRequest().getRequestURI();
    }

    @Override
    public String getServletPath() {
        return getRequest().getServletPath();
    }

    @Override
    public StringBuffer getRequestURL() {
        return getRequest().getRequestURL();
    }

    @Override
    public String getAuthType() {
        return getRequest().getAuthType();
    }

    @Override
    public Cookie[] getCookies() {
        synchronized (mutex) {
            return getRequest().getCookies();
        }
    }

    @Override
    public long getDateHeader(String name) {
        synchronized (mutex) {
            return getRequest().getDateHeader(name);
        }
    }

    @Override
    public String getHeader(String name) {
        synchronized (mutex) {
            return getRequest().getHeader(name);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Enumeration getHeaders(String name) {
        synchronized (mutex) {
            return getRequest().getHeaders(name);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Enumeration getHeaderNames() {
        synchronized (mutex) {
            return getRequest().getHeaderNames();
        }
    }

    @Override
    public int getIntHeader(String name) {
        synchronized (mutex) {
            return getRequest().getIntHeader(name);
        }
    }

    @Override
    public String getMethod() {
        return getRequest().getMethod();
    }

    @Override
    public String getPathInfo() {
        return getRequest().getPathInfo();
    }

    @Override
    public String getPathTranslated() {
        return getRequest().getPathTranslated();
    }

    @Override
    public String getRemoteUser() {
        return getRequest().getRemoteUser();
    }

    @Override
    public boolean isUserInRole(String role) {
        return getRequest().isUserInRole(role);
    }

    @Override
    public java.security.Principal getUserPrincipal() {
        return getRequest().getUserPrincipal();
    }

    @Override
    public String getRequestedSessionId() {
        return getRequest().getRequestedSessionId();
    }

    @Override
    public HttpSession getSession(boolean create) {
        return getRequest().getSession(create);
    }

    @Override
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return getRequest().isRequestedSessionIdValid();
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return getRequest().isRequestedSessionIdFromCookie();
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        return getRequest().isRequestedSessionIdFromURL();
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isRequestedSessionIdFromUrl() {
        return getRequest().isRequestedSessionIdFromUrl();
    }

    @Override
    public String getCharacterEncoding() {
        return getRequest().getCharacterEncoding();
    }

    @Override
    public void setCharacterEncoding(String enc) throws java.io.UnsupportedEncodingException {
        synchronized (mutex) {
            getRequest().setCharacterEncoding(enc);
        }
    }

    @Override
    public int getContentLength() {
        return getRequest().getContentLength();
    }

    @Override
    public String getContentType() {
        return getRequest().getContentType();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        synchronized (mutex) {
            return getRequest().getInputStream();
        }
    }

    @Override
    public String getParameter(String name) {
        synchronized (mutex) {
            return getRequest().getParameter(name);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public synchronized Map getParameterMap() {
        // 如果没有同步，tomcat下可能出现java.lang.IllegalStateException: No modifications are allowed to a locked ParameterMap
        // see http://code.google.com/p/paoding-rose/issues/detail?id=9
        return getRequest().getParameterMap();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Enumeration getParameterNames() {
        return getRequest().getParameterNames();
    }

    @Override
    public String[] getParameterValues(String name) {
        return getRequest().getParameterValues(name);
    }

    @Override
    public String getProtocol() {
        return getRequest().getProtocol();
    }

    @Override
    public String getScheme() {
        return getRequest().getScheme();
    }

    @Override
    public String getServerName() {
        return getRequest().getServerName();
    }

    @Override
    public int getServerPort() {
        return getRequest().getServerPort();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        synchronized (mutex) {
            return getRequest().getReader();
        }
    }

    @Override
    public String getRemoteAddr() {
        return getRequest().getRemoteAddr();
    }

    @Override
    public String getRemoteHost() {
        return getRequest().getRemoteHost();
    }

    @Override
    public Locale getLocale() {
        return getRequest().getLocale();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Enumeration getLocales() {
        return getRequest().getLocales();
    }

    @Override
    public boolean isSecure() {
        return getRequest().isSecure();
    }

    @SuppressWarnings("deprecation")
    @Override
    public String getRealPath(String path) {
        return getRequest().getRealPath(path);
    }

    @Override
    public int getRemotePort() {
        return getRequest().getRemotePort();
    }

    @Override
    public String getLocalName() {
        return getRequest().getLocalName();
    }

    @Override
    public String getLocalAddr() {
        return getRequest().getLocalAddr();
    }

    @Override
    public int getLocalPort() {
        return getRequest().getLocalPort();
    }
*/

	@Override
	public AsyncContext getAsyncContext() {
		return getRequest().getAsyncContext();
	}

	@Override
	public Object getAttribute(String arg0) {
		return getRequest().getAttribute(arg0);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		synchronized (mutex) {
			return getRequest().getAttributeNames();
		}
	}

	@Override
	public String getCharacterEncoding() {
		return getRequest().getCharacterEncoding();
	}

	@Override
	public int getContentLength() {
		return getRequest().getContentLength();
	}

	@Override
	public String getContentType() {
		return getRequest().getContentType();
	}

	@Override
	public DispatcherType getDispatcherType() {
		return getRequest().getDispatcherType();
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		synchronized (mutex) {
			return getRequest().getInputStream();
		}
	}

	@Override
	public String getLocalAddr() {
		return getRequest().getLocalAddr();
	}

	@Override
	public String getLocalName() {
		return getRequest().getLocalName();
	}

	@Override
	public int getLocalPort() {
		return getRequest().getLocalPort();
	}

	@Override
	public Locale getLocale() {
		return getRequest().getLocale();
	}

	@Override
	public Enumeration<Locale> getLocales() {
		return getRequest().getLocales();
	}

	@Override
	public String getParameter(String arg0) {
		synchronized (mutex) {
			return getRequest().getParameter(arg0);
		}
	}

	@Override
	public synchronized Map<String, String[]> getParameterMap() {
		// 如果没有同步，tomcat下可能出现java.lang.IllegalStateException: No modifications are allowed to a locked ParameterMap
        // see http://code.google.com/p/paoding-rose/issues/detail?id=9
		return getRequest().getParameterMap();
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return getRequest().getAttributeNames();
	}

	@Override
	public String[] getParameterValues(String arg0) {
		return getRequest().getParameterValues(arg0);
	}

	@Override
	public String getProtocol() {
		return getRequest().getProtocol();
	}

	@Override
	public BufferedReader getReader() throws IOException {
		synchronized (mutex) {
			return getRequest().getReader();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getRealPath(String arg0) {
		return getRequest().getRealPath(arg0);
	}

	@Override
	public String getRemoteAddr() {
		return getRequest().getRemoteAddr();
	}

	@Override
	public String getRemoteHost() {
		return getRequest().getRemoteHost();
	}

	@Override
	public int getRemotePort() {
		return getRequest().getRemotePort();
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String arg0) {
		return getRequest().getRequestDispatcher(arg0);
	}

	@Override
	public String getScheme() {
		return getRequest().getScheme();
	}

	@Override
	public String getServerName() {
		return getRequest().getServerName();
	}

	@Override
	public int getServerPort() {
		return getRequest().getServerPort();
	}

	@Override
	public ServletContext getServletContext() {
		return getRequest().getServletContext();
	}

	@Override
	public boolean isAsyncStarted() {
		return getRequest().isAsyncStarted();
	}

	@Override
	public boolean isAsyncSupported() {
		return getRequest().isAsyncStarted();
	}

	@Override
	public boolean isSecure() {
		return getRequest().isSecure();
	}

	@Override
	public void removeAttribute(String arg0) {
		synchronized (mutex) {
			getRequest().removeAttribute(arg0);
		}
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		synchronized (mutex) {
			getRequest().setAttribute(arg0, arg1);
		}
	}

	@Override
	public void setCharacterEncoding(String arg0)
			throws UnsupportedEncodingException {
		synchronized (mutex) {
			getRequest().setCharacterEncoding(arg0);
		}
	}

	@Override
	public AsyncContext startAsync() {
		return getRequest().startAsync();
	}

	@Override
	public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1) {
		return getRequest().startAsync(arg0, arg1);
	}

	@Override
	public boolean authenticate(HttpServletResponse arg0) throws IOException,
			ServletException {
		return getRequest().authenticate(arg0);
	}

	@Override
	public String getAuthType() {
		return getRequest().getAuthType();
	}

	@Override
	public String getContextPath() {
		return getRequest().getContextPath();
	}

	@Override
	public Cookie[] getCookies() {
		synchronized (mutex) {
			return getRequest().getCookies();
		}
	}

	@Override
	public long getDateHeader(String arg0) {
		synchronized (mutex) {
			return getRequest().getDateHeader(arg0);
		}
	}

	@Override
	public String getHeader(String arg0) {
		synchronized (mutex) {
			return getRequest().getHeader(arg0);
		}
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		synchronized (mutex) {
			return getRequest().getHeaderNames();
		}
	}

	@Override
	public Enumeration<String> getHeaders(String arg0) {
		synchronized (mutex) {
			return getRequest().getHeaders(arg0);
		}
	}

	@Override
	public int getIntHeader(String arg0) {
		synchronized (mutex) {
			return getRequest().getIntHeader(arg0);
		}
	}

	@Override
	public String getMethod() {
		return getRequest().getMethod();
	}

	@Override
	public Part getPart(String arg0) throws IOException, IllegalStateException,
			ServletException {
		return getRequest().getPart(arg0);
	}

	@Override
	public Collection<Part> getParts() throws IOException,
			IllegalStateException, ServletException {
		return getRequest().getParts();
	}

	@Override
	public String getPathInfo() {
		return getRequest().getPathInfo();
	}

	@Override
	public String getPathTranslated() {
		return getRequest().getPathTranslated();
	}

	@Override
	public String getQueryString() {
		return getRequest().getQueryString();
	}

	@Override
	public String getRemoteUser() {
		return getRequest().getRemoteUser();
	}

	@Override
	public String getRequestURI() {
		return getRequest().getRequestURI();
	}

	@Override
	public StringBuffer getRequestURL() {
		return getRequest().getRequestURL();
	}

	@Override
	public String getRequestedSessionId() {
		return getRequest().getRequestedSessionId();
	}

	@Override
	public String getServletPath() {
		return getRequest().getServletPath();
	}

	@Override
	public HttpSession getSession() {
		return getRequest().getSession();
	}

	@Override
	public HttpSession getSession(boolean arg0) {
		return getRequest().getSession(arg0);
	}

	@Override
	public Principal getUserPrincipal() {
		return getRequest().getUserPrincipal();
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		return getRequest().isRequestedSessionIdFromCookie();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isRequestedSessionIdFromURL() {
		return getRequest().isRequestedSessionIdFromUrl();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isRequestedSessionIdFromUrl() {
		return getRequest().isRequestedSessionIdFromUrl();
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		return getRequest().isRequestedSessionIdValid();
	}

	@Override
	public boolean isUserInRole(String arg0) {
		return getRequest().isUserInRole(arg0);
	}

	@Override
	public void login(String arg0, String arg1) throws ServletException {
		getRequest().login(arg0, arg1);
		
	}

	@Override
	public void logout() throws ServletException {
		getRequest().logout();
		
	}
}
