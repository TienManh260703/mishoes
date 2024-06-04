import * as authService from '../../service/authService'

const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
      const result = await authService.auth(username,  password);
      if(result && result.token){
        alert("Login success  : token :" + result.token)
      }else {
        setError('Đăng nhập thất bại. Vui lòng kiểm tra lại tên đăng nhập hoặc mật khẩu.');
      }  
  };

  return (
    <div className="login-form">
      <h2>Đăng Nhập</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Tên đăng nhập</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div>
          <label>Mật khẩu</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <button type="submit">Đăng nhập</button>
      </form>
    </div>
  );
};

export default LoginForm;
