import React from 'react';
import { Link } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import { ListGroupItem } from 'reactstrap';

const ApplicationItem = props => {
  const id = 1;
  const status = 'accepted';
  const { t } = useTranslation();

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
    <ListGroupItem className='row d-flex'>
      <div className='col-12 col-md-3'>Pelle Polle</div>
      <div className='col-12 col-md-3'>pelle@kth.se</div>
      <div className='col-12 col-md-3'>
        <span className={'badge ' + badgeStatus()}>{t(status)}</span>
      </div>
      <div className='col-12 col-md-3'>
        <Link to={`/applications/${id}`}>{t('view_application')}</Link>
      </div>
    </ListGroupItem>
  );
};

export default ApplicationItem;
