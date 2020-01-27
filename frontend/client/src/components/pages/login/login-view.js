import React from 'react';
import {
  Container,
  Button,
  Form,
  FormGroup,
  Label,
  Input,
  Alert,
  Spinner
} from 'reactstrap';
import { Link } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import './login.css';
import PropTypes from 'prop-types';

const LoginView = props => {
  const { t } = useTranslation();
  const {
    onSubmit,
    onChange,
    tryLogin,
    errorMessage,
    successMessage,
    renderRedirect
  } = props;
  return (
    <div className='login'>
      {renderRedirect()}
      <Container>
        <div className='light-overlay'>
          <Container className='form-container'>
            <div className='h3 text-center'>{t('login')}</div>
            {errorMessage ? <Alert color='danger'>{errorMessage}</Alert> : null}
            {successMessage ? (
              <Alert color='success'>{successMessage}</Alert>
            ) : null}
            <Form onSubmit={onSubmit}>
              <FormGroup className='mb-0'>
                <Label for='email'>{t('email')}</Label>
                <Input
                  type='email'
                  name='email'
                  id='email'
                  placeholder={t('email')}
                  autoComplete='username email'
                  className='mb-3'
                  onChange={onChange}
                />
                <Label for='password'>{t('password')}</Label>
                <Input
                  type='password'
                  name='password'
                  id='password'
                  placeholder={t('password')}
                  autoComplete='current-password'
                  className='mb-3'
                  onChange={onChange}
                />
                <Button color='dark' style={{ marginTop: '2rem' }} block>
                  {tryLogin ? <Spinner size='sm' /> : t('login')}
                </Button>
                <div className='text-center mt-3'>
                  {t('missing_account?')}{' '}
                  <Link to='/register'>{t('register')}</Link>
                </div>
              </FormGroup>
            </Form>
          </Container>
        </div>
      </Container>
    </div>
  );
};

LoginView.propTypes = {
  onSubmit: PropTypes.func.isRequired,
  onChange: PropTypes.func.isRequired,
  renderRedirect: PropTypes.func.isRequired,
  tryLogin: PropTypes.bool,
  errorMessage: PropTypes.string,
  successMessage: PropTypes.string
};

export default LoginView;
