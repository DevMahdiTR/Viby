import './tablecoupons.scss';
import image1 from '../1.jpg';
import image2 from '../2.jpg';
import image3 from '../3.jpg';
import image4 from '../4.jpg';
import image5 from '../5.jpg';
import image6 from '../6.jpg';
const TableCoupons = () => {

    return (
        <div>
        <div className="table">
            <div className="table__container">
                <img src= {image1}alt="" />
                <h6 className='aa'>50%</h6>
                <h5 className='hh'>4000 points</h5>
            </div>
            <div className="table__container">
                <img src= {image2}alt="" />
                <h6 className='aa'>20%</h6>
                <h5 className='hh'>2500 points</h5>
            </div>
            <div className="table__container">
                <img src= {image3}alt="" />
                <h6 className='aa'>20%</h6>
                <h5 className='hh'>500 points</h5>
            </div>
            
        </div>
        <div className="table ffff" >
        <div className="table__container">
            <img src= {image4}alt="" />
            <h6 className='aa'>20%</h6>
            <h5 className='hh'>500 points</h5>
        </div>
        <div className="table__container">
            <img src= {image5}alt="" />
            <h6 className='aa'>10%</h6>
            <h5 className='hh'>500 points</h5>
        </div>
        <div className="table__container">
            <img src= {image6}alt="" />
            <h6 className='aa'>40%</h6>
            <h5 className='hh'>6500 points</h5>
        </div>
        
    </div>
    </div>
    );
}

export default TableCoupons;