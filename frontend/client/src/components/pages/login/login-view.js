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
    errorMessageKey,
    successMessageKey,
    renderRedirect
  } = props;
  return (
    <div className='login'>
      {renderRedirect()}
      <Container>
        <Container className='form-container'>
          <div className='h3 text-center'>{t('login')}</div>
          {errorMessageKey ? (
            <Alert color='danger'>{t(errorMessageKey)}</Alert>
          ) : null}
          {successMessageKey ? (
            <Alert color='success'>{t(successMessageKey)}</Alert>
          ) : null}
          <Form onSubmit={onSubmit}>
            <FormGroup className='mb-0'>
              <Label for='username'>{t('personal.username')}</Label>
              <Input
                type='text'
                name='username'
                id='username'
                placeholder={t('personal.username')}
                autoComplete='username'
                className='mb-3'
                onChange={onChange}
              />
              <Label for='password'>{t('personal.password')}</Label>
              <Input
                type='password'
                name='password'
                id='password'
                placeholder={t('personal.password')}
                autoComplete='current-password'
                className='mb-3'
                onChange={onChange}
              />
              <Button color='dark' style={{ marginTop: '2rem' }} block>
                {tryLogin ? <Spinner size='sm' /> : t('login')}
              </Button>
              <div className='text-center mt-3'>
                {t('form.missing_account?')}{' '}
                <Link to='/register'>{t('register')}</Link>
              </div>
            </FormGroup>
          </Form>
        </Container>
      </Container>
    </div>
  );
};

LoginView.propTypes = {
  onSubmit: PropTypes.func.isRequired,
  onChange: PropTypes.func.isRequired,
  renderRedirect: PropTypes.func.isRequired,
  tryLogin: PropTypes.bool,
  errorMessageKey: PropTypes.string,
  successMessageKey: PropTypes.string
};

export default LoginView;
