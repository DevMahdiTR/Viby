import "./navbar.scss";
import pdp from '../../formule-dejeuner-a-gourmandise.jpg';
import logo from '../../V (1).png';
const Navbar = () => {
  return (
    <div className="navbar">
      <div className="logo">
        <img src={logo} alt="" style={{width : "50px" , height : "50px"}}/>
        
      </div>
      <div className="icons">
        <img src="/search.svg" alt="" className="icon" />
        <img src="/app.svg" alt="" className="icon" />
        <img src="/expand.svg" alt="" className="icon" />
        <div className="notification">
          <img src="/notifications.svg" alt="" />
          <span>1</span>
        </div>
        <div className="user">
          <img
            src="https://www.keejob.com/media/recruiter/recruiter_148/logo-148-20160328-135428.jpg"
            alt=""
          />
          <span>Gourmandise</span>
        </div>
        <img src="/settings.svg" alt="" className="icon" />
      </div>
    </div>
  );
};

export default Navbar;
