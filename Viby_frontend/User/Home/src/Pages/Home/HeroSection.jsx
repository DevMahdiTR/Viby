export default function HeroSection() {
  return (
    <section id="heroSection" className="hero--section">
      <div className="hero--section--content--box">
        <div className="hero--section--content">
          <h1 className="hero--section--title">
            <span className="hero--section-title--color">VIBY</span>{" "}
          </h1>
          <p className="hero--section-description">
          We revolutionize marketing by seamlessly integrating gamification and blockchain, creating engaging and rewarding brand experiences.
          </p>
        </div>
        <button className="btn btn-primary">Get In Touch</button>
      </div>
      <div className="hero--section--img">
      <img src="./img/logo.png" alt="Logoipsum"  width={100}/>
      </div>
    </section>
  );
}
