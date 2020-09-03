import React from 'react';
interface Message{
    message:string
}

class Welcome extends React.Component<Message> {
    render() {
      return (
        <div>
          {this.props.message}
        </div>
      );
    }
  }
  export default Welcome;