'use client';

import { Button } from '@/components/ui/button';

type Props = {
  children: string;
  className?: string;
};

export default function ButtonAuthGoogle({ children, className }: Props) {

  const onClick = () => {
    const CLIENT_ID = process.env.NEXT_PUBLIC_CLIENT_ID;
    const CALLBACK_URL = process.env.NEXT_PUBLIC_REDIRECT_URI;
    const AUTH_URL = process.env.NEXT_PUBLIC_AUTH_URI;

    const TARGET_URL = `${AUTH_URL}?redirect_uri=${encodeURIComponent(CALLBACK_URL as string)
      }&response_type=code&client_id=${CLIENT_ID
      }&scope=openid%20email%20profile`;

    window.location.href = TARGET_URL;
  };

  return (
    <Button type="button" variant="outline" className={className || ''} onClick={onClick}>
      {children}
    </Button>
  );
}
