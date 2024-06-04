import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import LoginForm from './components/Login/LoginForm';
import { Button } from 'antd';

function App() {

  return (
    <>
      <Button type="danger">Primary Button</Button>
      <LoginForm></LoginForm>    
    </>
  )
}

export default App
