import * as React from 'react';
import { User } from '../user/user.model';
import Welcome from '../welcome';
import './Login.css';

interface MyProps {
}

interface LoginDTO {
  username: string,
  password: string,
  jwt?:string,
  welcomeMessage:string
}
class Login extends React.Component<MyProps,LoginDTO> {
  constructor(props:any) {
    super(props);
    this.state = {username: '',password:'',welcomeMessage:''};
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange = (event:any) => {
    let val:string = event.target.value;
    let nam:string = event.target.name;
    let temporary: any = {};
    temporary[nam] = val;
    this.setState(temporary);

  }

  handleSubmit(event:any) {
    let user:User = new User(this.state.username,this.state.password);
    console.log('A username and password was submitted: ' + this.state.username+' '+this.state.password);
    event.preventDefault();
    this.authenticate(user);   
  }

  private authenticate(user: User) {
    fetch('http://localhost:9091/authenticate', {
      method: 'POST',
      headers: { 'Content-type': 'application/json' },
      body: JSON.stringify(user)
    })
      .then(res => res.json())
      .then(
        (result) => {
          if (result.jwt) {
            console.log('jwt: ' + result.jwt);
            this.setState({ jwt: result.jwt });
            this.setWelcomeMessage();
          }
        }
        );
  }

  private setWelcomeMessage(){
    fetch('http://localhost:9091/hello', {
      method: 'GET',
      headers: { 'Content-type': 'application/json','Authorization': 'Bearer '+ this.state.jwt }
    })
      .then(res => res.json())
      .then(
        (result) => {
          if (result.message) {
            console.log('welcome message: ' + result.message);
            this.setState({ welcomeMessage: result.message + '!! You have successfully logged In'});
          }
        }
      );
  }
  render() {
    return (
      <div>
      <form onSubmit={this.handleSubmit}>
        <label>
          Username:
          <br/>
          <input type="text" name="username" onChange={this.handleChange} />
        </label>
        <br/>
        <label>
          Password:
          <br/>
          <input type="text" name="password" onChange={this.handleChange} />
        </label>
        <br/>
        <input type="submit" value="Submit" />
      </form>,
      <Welcome message= {this.state.welcomeMessage}/>
      </div>
    );
  }

}
export default Login;