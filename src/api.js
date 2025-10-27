const API_BASE_URL = 'http://localhost:8080';

async function request(url, method = 'GET', data = null) {
  const options = {
    method,
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
  };

  if (data) {
    const formData = new URLSearchParams();
    Object.entries(data).forEach(([key, value]) => {
      if (value !== null && value !== undefined) {
        formData.append(key, value);
      }
    });
    options.body = formData;
  }

  try {
    const response = await fetch(`${API_BASE_URL}${url}`, options);
    const result = await response.json();
    
    if (!response.ok) {
      throw new Error(result.message || '操作失败');
    }
    return result;
  } catch (error) {
    throw error;
  }
}

export const userApi = {
  register: (userData) => request('/user/register', 'POST', userData),
  login: (username, password) => request('/user/login', 'POST', { username, password }),
  getUserInfo: (id) => request(`/user/${id}`),
  updateUser: (userData) => request('/user', 'PUT', userData),
  resetPassword: (id, oldPassword, newPassword) => 
      request('/user/password', 'PUT', { id, oldPassword, newPassword })
};

export const auth = {
  setUser(user) {
    localStorage.setItem('user', JSON.stringify(user));
  },
  getUser() {
    const userStr = localStorage.getItem('user');
    return userStr ? JSON.parse(userStr) : null;
  },
  logout() {
    localStorage.removeItem('user');
    window.location.href = '/login';
  },
  isAuthenticated() {
    return !!this.getUser();
  }
};