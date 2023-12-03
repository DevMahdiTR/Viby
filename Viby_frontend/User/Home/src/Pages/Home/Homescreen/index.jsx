import AboutMe from "../AboutMe";
import ContactMe from "../ContactMe";
import Footer from "../Footer";
import HeroSection from "../HeroSection";
import Pricing from "../Prcing";
import Testimonial from "../Testimonials";

export default function Home() {
  return (
    <>
      <HeroSection />
      <AboutMe />
      <Pricing />
      <ContactMe />
      <Testimonial />
      
      <Footer />
    </>
  );
}
