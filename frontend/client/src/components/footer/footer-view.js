import React from 'react';
import { Container } from 'reactstrap';
import { useTranslation } from 'react-i18next';

import './footer.css';

/**
 * Holds the view of the footer.
 */
const FooterView = () => {
  const { t } = useTranslation();

  return (
    <footer id='main-footer' className='bg-dark'>
      <Container>
        <div className='row'>
          <div className='col text-center'>
            <div className='pt-2'>
              <h5>{t('brand')}</h5>
              <p className='footer-text'>{t('copyright')} &copy; 2020 </p>
            </div>
          </div>
        </div>
      </Container>
    </footer>
  );
};

export default FooterView;
