import React from 'react';
import { Link } from 'react-router-dom';
import { Container, Button, ListGroup, ListGroupItem } from 'reactstrap';
import { useTranslation } from 'react-i18next';

import './application.css';

const ApplicationView = props => {
  const { t } = useTranslation();

  const status = 'rejected';

  const id = props.id;

  const badgeStatus = () => {
    if (status === 'accepted') {
      return 'badge-success';
    } else if (status === 'rejected') {
      return 'badge-danger';
    } else {
      return 'badge-secondary';
    }
  };

  return (
    <Container>
      <div className='application'>
        <div className='back'>
          <Link to='/applications'>
            <i className='fas fa-angle-double-left'></i>{' '}
            <span className='back-text'>{t('back_to_applications')}</span>
          </Link>
        </div>
        <h1>
          {t('application')} #{id}
        </h1>
        <ListGroup className='list-group-horizontal-md'>
          <ListGroupItem>
            <span className='bold'>{t('status')}: </span>
            <span className={'badge ' + badgeStatus()}>{t(status)}</span>
          </ListGroupItem>
        </ListGroup>
        <div className='personal-info mt-3'>
          <h2>{t('personal_info')}</h2>
          <ListGroup className='list-group-horizontal-lg'>
            <ListGroupItem>
              <span className='bold'>{t('first_name')}: </span>Pelle
            </ListGroupItem>
            <ListGroupItem>
              <span className='bold'>{t('last_name')}: </span>Polle
            </ListGroupItem>
            <ListGroupItem>
              <span className='bold'>{t('birth_date')}: </span>1997-06-02
            </ListGroupItem>
            <ListGroupItem>
              <span className='bold'>{t('email')}: </span>pelle@kth.se
            </ListGroupItem>
          </ListGroup>
        </div>
        <div className='experience mt-2'>
          <h2>{t('experience')}</h2>
          <ListGroup className='list-group list-group-horizontal-lg'>
            <ListGroupItem>
              <span className='bold'>Baking</span>
            </ListGroupItem>
            <ListGroupItem>
              <span className='bold'>Drinking</span>
            </ListGroupItem>
            <ListGroupItem>
              <span className='bold'>Driving</span>
            </ListGroupItem>
          </ListGroup>
        </div>
        <div className='availibility mt-2'>
          <h2>{t('availability')}</h2>
          <ListGroup className='list-group-horizontal-lg'>
            <ListGroupItem>
              <span className='bold'>{t('from')} </span> 2020-02-10{' '}
              <span className='bold'>{t('to')} </span> 2020-03-10
            </ListGroupItem>
            <ListGroupItem>
              <span className='bold'>{t('from')} </span> 2020-05-10{' '}
              <span className='bold'>{t('to')} </span> 2020-06-10
            </ListGroupItem>
          </ListGroup>
        </div>
        <div className='actions mt-3'>
          <Button className='btn-success mr-1'>{t('accept')}</Button>
          <Button className='btn-danger mr-1'>{t('reject')}</Button>
          <Button className='secondary'>{t('unhandle')}</Button>
        </div>
      </div>
    </Container>
  );
};

ApplicationView.propTypes = {};

export default ApplicationView;
